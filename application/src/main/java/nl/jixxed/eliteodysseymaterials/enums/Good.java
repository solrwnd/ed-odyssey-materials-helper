package nl.jixxed.eliteodysseymaterials.enums;

import nl.jixxed.eliteodysseymaterials.service.LocaleService;

public enum Good implements OdysseyMaterial {
    AGRICULTURALPROCESSSAMPLE(false),
    BIOCHEMICALAGENT(true),
    BIOMECHANICALCOMPONENT(false),
    BUILDINGSCHEMATIC(false),
    CALIFORNIUM(false),
    CASTFOSSIL(false),
    CHEMICALPROCESSSAMPLE(false),
    CHEMICALSAMPLE(false),
    COMPACTLIBRARY(false),
    COMPRESSIONLIQUEFIEDGAS(false),
    DEEPMANTLESAMPLE(false),
    DEGRADEDPOWERREGULATOR(false),
    GENETICREPAIRMEDS(false),
    GENETICSAMPLE(false),
    GMEDS(false),
    HEALTHMONITOR(false),
    HUSH(true),
    INERTIACANISTER(false),
    INFINITY(false),
    INORGANICCONTAMINANT(true),
    INSIGHT(false),
    INSIGHTDATABANK(false),
    INSIGHTENTERTAINMENTSUITE(false),
    IONISEDGAS(false),
    LARGECAPACITYPOWERREGULATOR(false),
    LAZARUS(true),
    MICROBIALINHIBITOR(false),
    MUTAGENICCATALYST(true),
    NUTRITIONALCONCENTRATE(false),
    PERSONALCOMPUTER(false),
    PERSONALDOCUMENTS(false),
    PETRIFIEDFOSSIL(false),
    PUSH(true),
    PYROLYTICCATALYST(true),
    REFINEMENTPROCESSSAMPLE(false),
    SABOTAGEDCOMPONENT(false),
    SHIPSCHEMATIC(false),
    SUITSCHEMATIC(false),
    SURVEILLANCEEQUIPMENT(false),
    SYNTHETICGENOME(false),
    SYNTHETICPATHOGEN(true),
    TRUEFORMFOSSIL(false),
    UNIVERSALTRANSLATOR(false),
    VEHICLESCHEMATIC(false),
    WEAPONSCHEMATIC(false),
    POWERINDUSTRIAL(false, true),
    POWERMISCINDUST(false, true),
    POWERINVENTORY(false, true),
    POWERPLAYMILITARY(false, true),
    POWERELECTRONICS(false, true),
    POWERCOMPUTER(false, true),
    POWEREXPERIMENT(false, true),
    POWERAGRICULTURE(false, true),
    POWERMEDICAL(false, true),
    POWEREXTRACTION(false, true),
    POWERMISCCOMPUTER(false, true),
    POWERSECURITY(false, true),
    POWERPOWER(false, true),
    POWEREQUIPMENT(false, true),
    POWERRESEARCH(false, true),
    UNKNOWN(false);
    private final boolean illegal;
    private boolean powerplay = false;

    Good(final boolean illegal) {
        this.illegal = illegal;
    }

    Good(final boolean illegal, final boolean powerplay) {
        this(illegal);
        this.powerplay = powerplay;
    }

    public static Good forName(final String name) {
        try {
            return Good.valueOf(name.toUpperCase());
        } catch (final IllegalArgumentException ex) {
            return Good.UNKNOWN;
        }
    }

    @Override
    public OdysseyStorageType getStorageType() {
        return OdysseyStorageType.GOOD;
    }

    @Override
    public String getLocalizationKey() {
        return "material.good." + this.name().toLowerCase();
    }

    @Override
    public boolean isUnknown() {
        return this == Good.UNKNOWN;
    }

    @Override
    public boolean isIllegal() {
        return this.illegal;
    }

    @Override
    public boolean isPowerplay() {
        return this.powerplay;
    }

    @Override
    public String getTypeNameLocalized() {
        return LocaleService.getLocalizedStringForCurrentLocale("material.asset.type.good");
    }

    @Override
    public String toString() {
        return LocaleService.getLocalizedStringForCurrentLocale(getLocalizationKey());
    }
}
