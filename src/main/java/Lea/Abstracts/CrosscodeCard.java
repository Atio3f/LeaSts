package Lea.Abstracts;

import Lea.characters.Lea;
import Lea.enums.customEnums;
import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.variables.ExhaustiveVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;
import java.util.List;


public abstract class CrosscodeCard extends CustomCard {
    protected int SP_Cost;//Indique le nombre de SP pour pouvoir utiliser la compétence
    public int BASESP_Gain; //Indique le nombre de SP de base de la carte
    protected int SP_Gain;//Indique le nombre de SP gagné lors de l'utilisation de cette carte

    protected int ElementalCost = -1;  //Indique le coût en énergie de la carte -> -1 == pas de coût
    protected String ElementCard = "Neutral";

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
        List<String> descriptors = new ArrayList<>();
        //Add an indicator that the card is a combat art and help to know its category
        if (this.tags.contains(customEnums.COMBAT_ART_STARTER) || this.tags.contains(customEnums.COMBAT_ART_T1)) {
            descriptors.add("CombatArtT1");
        } else if (this.tags.contains(customEnums.COMBAT_ART_T2)) {
            descriptors.add("CombatArtT2");
        }else if (this.tags.contains(customEnums.COMBAT_ART_T3)) {
            descriptors.add("CombatArtT3");
        }


        return descriptors;
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
