package nl.jixxed.eliteodysseymaterials.templates;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.schedulers.Schedulers;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import nl.jixxed.eliteodysseymaterials.domain.Search;
import nl.jixxed.eliteodysseymaterials.enums.Show;
import nl.jixxed.eliteodysseymaterials.enums.Sort;
import nl.jixxed.eliteodysseymaterials.service.LocaleService;
import nl.jixxed.eliteodysseymaterials.service.PreferencesService;
import nl.jixxed.eliteodysseymaterials.service.event.EventService;
import nl.jixxed.eliteodysseymaterials.service.event.SearchEvent;

import java.util.concurrent.TimeUnit;

public class SearchBar extends HBox {

    final Button button = new Button();
    final TextField textField = createSearchTextField();


    final ComboBox<Show> showMaterialsComboBox = createShowComboBox();
    final ComboBox<Sort> sortMaterialsComboBox = createSortComboBox();

    public SearchBar() {
        super();

        this.button.setText((PreferencesService.getPreference("recipes.visible", Boolean.TRUE)) ? "<" : ">");
        this.button.getStyleClass().add("menubutton");


        setDefaultOptions();
        HBox.setHgrow(this.textField, Priority.ALWAYS);
        HBox.setHgrow(this.showMaterialsComboBox, Priority.ALWAYS);
        HBox.setHgrow(this.sortMaterialsComboBox, Priority.ALWAYS);
        this.getChildren().addAll(this.button, this.textField, this.showMaterialsComboBox, this.sortMaterialsComboBox);
    }

    private void setDefaultOptions() {
        try {
            final Sort sort = Sort.valueOf(PreferencesService.getPreference("search.sort", ""));
            this.sortMaterialsComboBox.getSelectionModel().select(sort);
        } catch (final IllegalArgumentException ex) {

        }

        try {
            final Show filter = Show.valueOf(PreferencesService.getPreference("search.filter", ""));
            this.showMaterialsComboBox.getSelectionModel().select(filter);
        } catch (final IllegalArgumentException ex) {

        }
    }

    private TextField createSearchTextField() {
        final TextField textField = new TextField();
        textField.getStyleClass().add("search-input");
        textField.promptTextProperty().bind(LocaleService.getStringBinding("search.text.placeholder"));
        textField.setFocusTraversable(false);

        Observable.create((ObservableEmitter<String> emitter) -> textField.textProperty().addListener((observable, oldValue, newValue) -> emitter.onNext(newValue)))
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.io())
                .subscribe((newValue) -> {
                    EventService.publish(new SearchEvent(new Search(newValue, getSortOrDefault(this.sortMaterialsComboBox), getShowOrDefault(this.showMaterialsComboBox))));
                });
        return textField;
    }

    private ComboBox<Sort> createSortComboBox() {
        final ComboBox<Sort> sortMaterialsComboBox = new ComboBox<>();
        sortMaterialsComboBox.itemsProperty().bind(LocaleService.getListBinding(Sort.ENGINEER_BLUEPRINT_IRRELEVANT, Sort.RELEVANT_IRRELEVANT, Sort.ALPHABETICAL, Sort.QUANTITY));
        sortMaterialsComboBox.getStyleClass().add("filter-and-sort");
        sortMaterialsComboBox.promptTextProperty().bind(LocaleService.getStringBinding("search.sort.placeholder"));
        final Tooltip sortMaterialsTooltip = new Tooltip();
        sortMaterialsTooltip.textProperty().bind(LocaleService.getStringBinding("search.sort.placeholder"));
        sortMaterialsComboBox.setTooltip(sortMaterialsTooltip);
        sortMaterialsComboBox.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            if (newValue != null) {
                EventService.publish(new SearchEvent(new Search(getQueryOrDefault(this.textField), newValue, getShowOrDefault(this.showMaterialsComboBox))));
                PreferencesService.setPreference("search.sort", newValue.name());
            }
        });
        return sortMaterialsComboBox;
    }

    private ComboBox<Show> createShowComboBox() {
        final ComboBox<Show> showMaterialsComboBox = new ComboBox<>();
        showMaterialsComboBox.itemsProperty().bind(LocaleService.getListBinding(Show.ALL,
                Show.ALL_WITH_STOCK,
                Show.ALL_ENGINEER_BLUEPRINT,
                Show.REQUIRED_ENGINEER_BLUEPRINT,
                Show.ALL_ENGINEER,
                Show.REQUIRED_ENGINEER,
                Show.BLUEPRINT,
                Show.IRRELEVANT,
                Show.IRRELEVANT_WITH_STOCK,
                Show.FAVOURITES));
        showMaterialsComboBox.getStyleClass().add("filter-and-sort");
        showMaterialsComboBox.promptTextProperty().bind(LocaleService.getStringBinding("search.filter.placeholder"));
        final Tooltip showMaterialsTooltip = new Tooltip();
        showMaterialsTooltip.textProperty().bind(LocaleService.getStringBinding("search.filter.placeholder"));
        showMaterialsComboBox.setTooltip(showMaterialsTooltip);
        showMaterialsComboBox.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            if (newValue != null) {
                EventService.publish(new SearchEvent(new Search(getQueryOrDefault(this.textField), getSortOrDefault(this.sortMaterialsComboBox), newValue)));
                PreferencesService.setPreference("search.filter", newValue.name());
            }
        });
        return showMaterialsComboBox;
    }

    private String getQueryOrDefault(final TextField textField) {
        return (textField.getText() != null) ? textField.getText() : "";
    }

    private Show getShowOrDefault(final ComboBox<Show> showMaterialsComboBox) {
        return (showMaterialsComboBox.getValue() != null) ? showMaterialsComboBox.getValue() : Show.ALL;
    }

    private Sort getSortOrDefault(final ComboBox<Sort> sortMaterialsComboBox) {
        return (sortMaterialsComboBox.getValue() != null) ? sortMaterialsComboBox.getValue() : Sort.ALPHABETICAL;
    }

    public Button getButton() {
        return this.button;
    }
}
