package Lea.enums;

import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;

//Thanks to alchyr https://github.com/Alchyr/Astrologer/blob/master/src/main/java/Astrologer/Enums/CustomTags.java
//Contient les nouveaux tags ajoutés. Element de la carte ainsi que le type de la compétence
public class customEnums {
    //Element de la carte
    @SpireEnum
    public static AbstractCard.CardTags NEUTRAL;
    @SpireEnum
    public static AbstractCard.CardTags COLD;
    @SpireEnum
    public static AbstractCard.CardTags HEAT;
    @SpireEnum
    public static AbstractCard.CardTags SHOCK;
    @SpireEnum
    public static AbstractCard.CardTags WAVE;

    //Type de compétence
    @SpireEnum
    public static AbstractCard.CardTags MELEE;
    @SpireEnum
    public static AbstractCard.CardTags BULLET;
    @SpireEnum
    public static AbstractCard.CardTags SHIELD;
    @SpireEnum
    public static AbstractCard.CardTags DASH;
    @SpireEnum
    public static AbstractCard.CardTags COMBAT_ART;
    @SpireEnum
    public static AbstractCard.CardTags COMPANION;

    //Combat arts rarity, allow to separate their pools to avoid people to get too many of them
    @SpireEnum
    public static AbstractCard.CardRarity COMBAT_ART_STARTER;
    @SpireEnum
    public static AbstractCard.CardRarity COMBAT_ART_T1;
    @SpireEnum
    public static AbstractCard.CardRarity COMBAT_ART_T2;
    @SpireEnum
    public static AbstractCard.CardRarity COMBAT_ART_T3;
}
