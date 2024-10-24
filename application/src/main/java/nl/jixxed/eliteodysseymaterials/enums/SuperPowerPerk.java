package nl.jixxed.eliteodysseymaterials.enums;

public enum SuperPowerPerk {
    RANK_DECAL,
    REDUCED_REBUY_BY_OPPOSING_POWER,
    REDUCED_REBUY_IN_OWN_TERRITORY,
    REDUCED_WEAPON_MODULE_COST_IN_OWN_TERRITORY,
    REDUCED_REARM_PRICES_IN_OWN_TERRITORY,
    REDUCED_BOUNTY_VALUE_IN_OWN_TERRITORY,
    REDUCED_REFUEL_PRICES_IN_OWN_TERRITORY,
    REDUCED_REPAIR_PRICES_IN_OWN_TERRITORY,
    INCREASE_FOOD_AND_MEDICINE_PROFIT,
    INCREASE_MINOR_FACTION_REPUTATION,
    INCREASE_SALVAGE_PROFIT,
    INCREASE_BOUNTY_PAYOUT,
    INCREASE_SEARCH_AND_RESCUE_PAYOUT,
    INCREASE_MINING_COMMODITY_PAYOUT,
    INCREASE_IMPERIAL_SLAVES_COMMODITY_PAYOUT,
    INCREASE_TRADE_BOND_SALES_AWARD,
    INCREASE_TRADE_BOND_RARE_GOODS_AWARD,
    INCREASE_ORGANICS_DATA_SALES,
    INCREASE_TECHNOLOGY_COMMODITY_PROFIT,
    INCREASE_BLACK_MARKET_PROFITS,
    INCREASE_EXPLORATION_DATA_SALES;

    public String getLocalizationKey() {
        return "superpower.perk." + this.name().toLowerCase();
    }
}