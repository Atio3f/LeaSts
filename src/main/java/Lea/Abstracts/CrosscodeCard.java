package Lea.Abstracts;

import Lea.characters.Lea;
import Lea.enums.customEnums;
import Lea.patches.LeaEnum;
import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.variables.ExhaustiveVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public abstract class CrosscodeCard extends CustomCard {
    protected int SP_Cost;//Indique le nombre de SP pour pouvoir utiliser la compétence
    public int BASESP_Gain; //Indique le nombre de SP de base de la carte
    protected int SP_Gain;//Indique le nombre de SP gagné lors de l'utilisation de cette carte

    protected int ElementalCost = -1;  //Indique le coût en énergie de la carte -> -1 == pas de coût
    protected String ElementCard = "Neutral";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("leacrosscode:card");
    protected static String[] EXTENDED_DESCRIPTION = {cardStrings.EXTENDED_DESCRIPTION[0], cardStrings.EXTENDED_DESCRIPTION[1], cardStrings.EXTENDED_DESCRIPTION[2]};
    public CrosscodeCard(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target, int sp_cost, int sp_gain, int elementalCost) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
        SP_Cost = sp_cost;
        SP_Gain = sp_gain;
        BASESP_Gain = sp_gain;
        ElementalCost = elementalCost;
    }
    //Constructeur sans coût en énergie élémentaire
    public CrosscodeCard(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target, int sp_cost, int sp_gain) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
        SP_Cost = sp_cost;
        SP_Gain = sp_gain;
        BASESP_Gain = sp_gain;
    }

    public CrosscodeCard(String id, String name, RegionName img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
    }

    @Override
    public void upgrade() {

    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster){
        if(abstractPlayer.getClass().getSuperclass().getSimpleName().equals("CrosscodeCharacter")){
            useCost((CrosscodeCharacter) abstractPlayer);
        }else{
            LogManager.getLogger(Lea.class.getName()).info("PROB DE CLASSE PERSO------------------------------------------------------");
            overloadExhaust();
        }
    }

    @Override
    public List<String> getCardDescriptors() {
        if (rarity == customEnums.COMBAT_ART_STARTER | rarity == customEnums.COMBAT_ART_T1 ) {
            return Collections.singletonList(EXTENDED_DESCRIPTION[0]);
        } else if (rarity == customEnums.COMBAT_ART_T2) {
            return Collections.singletonList(EXTENDED_DESCRIPTION[1]);
        } else if (rarity == customEnums.COMBAT_ART_T3) {
            return Collections.singletonList(EXTENDED_DESCRIPTION[2]);
        }
        return Collections.emptyList();
    }


    //S'occupe des coûts élémentaires et en SP ainsi que le gain de SP avec l'attaque
    protected void useCost(CrosscodeCharacter p){
        p.spendingElementalSP(this, SP_Cost, SP_Gain, ElementCard, ElementalCost);
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        //Si le personnage est un perso de Crosscode alors on utilise une fonction faite maison
        if(p.getClass().getSuperclass().getSimpleName().equals("CrosscodeCharacter")){
            return canUse((CrosscodeCharacter)p, m);
        }else{
            if (this.type == AbstractCard.CardType.STATUS && this.costForTurn < -1 && !AbstractDungeon.player.hasRelic("Medical Kit")) {
                return false;
            } else if (this.type == AbstractCard.CardType.CURSE && this.costForTurn < -1 && !AbstractDungeon.player.hasRelic("Blue Candle")) {
                return false;
            } else {
                return this.cardPlayable(m) && this.hasEnoughEnergy();
            }
        }

    }

    public boolean canUse(CrosscodeCharacter p, AbstractMonster m) {
        if (this.type == AbstractCard.CardType.STATUS && this.costForTurn < -1 && !AbstractDungeon.player.hasRelic("Medical Kit")) {
            return false;
        } else if (this.type == AbstractCard.CardType.CURSE && this.costForTurn < -1 && !AbstractDungeon.player.hasRelic("Blue Candle")) {
            return false;
        } else {
            return this.cardPlayable(m) && this.hasEnoughEnergy() && this.hasEnoughSP(p);
        }
    }

    //
    private boolean hasEnoughSP(CrosscodeCharacter p) {
        if(p.SP_fight < SP_Cost){
            this.cantUseMessage = "Not enough SP...";
            return false;
        }else{
            return true;
        }
    }

    public int getSP_Gain(){
        return SP_Gain;
    }

    //Lorsque le joueur est en surcharge élémentaire avec un élément les cartes élémentaires qu'il joue sont exhaust avec cette fonction
    public void overloadExhaust() {
        this.exhaust = true;
    }
}
