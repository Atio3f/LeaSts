package Lea.Abstracts;

import Lea.characters.Lea;
import Lea.powers.*;
import basemod.abstracts.CustomPlayer;
import basemod.animations.AbstractAnimation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.ui.panels.energyorb.EnergyOrbInterface;
import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;


//Permet de rajouter un nouveau gestionnaire de ressources (pour les SP des arts de combat)
public abstract class CrosscodeCharacter extends CustomPlayer {

    protected final int BASE_MAX_SP = 8;
    protected int Max_SP;
    protected int SP_fight = 0;    //SP dans le fight actuel
    String[] elements = {"Heat", "Cold", "Shock", "Wave"};
    String[] elementsStatus = {"Burn", "Chill", "Jolt", "Mark"};
    public Integer[] elementsValeur = {0, 0, 0, 0};

    public CrosscodeCharacter(String name, PlayerClass playerClass, String[] orbTextures, String orbVfxPath, String model, String animation) {
        super(name, playerClass, orbTextures, orbVfxPath, model, animation);
    }

    public CrosscodeCharacter(String name, PlayerClass playerClass, EnergyOrbInterface energyOrbInterface, String model, String animation) {
        super(name, playerClass, energyOrbInterface, model, animation);
    }

    public CrosscodeCharacter(String name, PlayerClass playerClass, String[] orbTextures, String orbVfxPath, float[] layerSpeeds, String model, String animation) {
        super(name, playerClass, orbTextures, orbVfxPath, layerSpeeds, model, animation);
    }

    public CrosscodeCharacter(String name, PlayerClass playerClass, String[] orbTextures, String orbVfxPath, AbstractAnimation animation) {
        super(name, playerClass, orbTextures, orbVfxPath, animation);
    }

    public CrosscodeCharacter(String name, PlayerClass playerClass, String[] orbTextures, String orbVfxPath, float[] layerSpeeds, AbstractAnimation animation) {
        super(name, playerClass, orbTextures, orbVfxPath, layerSpeeds, animation);
        Max_SP = BASE_MAX_SP;
    }

    public CrosscodeCharacter(String name, PlayerClass playerClass, EnergyOrbInterface energyOrbInterface, AbstractAnimation animation) {
        super(name, playerClass, energyOrbInterface, animation);
    }

    @Override
    public void applyPreCombatLogic(){
        SP_fight = 0;
        elementsValeur = new Integer[]{0, 0, 0, 0};
        LogManager.getLogger(Lea.class.getName()).info("ELEMENTS : " +  elementsValeur[0] + " " + elementsValeur[1]+" " + elementsValeur[2] + " " + elementsValeur[3]);

        super.applyPreCombatLogic();
    }

    /**
     * Permet d'estimer si la carte doit être exhaust ou pas en fonction de l'énergie élémentaire restante
     * @param element
     * @param valeur elemental cost on the specified element
     * @return
     */
    public boolean hasEnoughElem(String element, int valeur){
        int indexOfElem = indexOfElement(element);
        if(indexOfElem != -1 && elementsValeur[indexOfElem] >= valeur){
            return true;
        }else{
            return false;
        }
    }
    public int indexOfElement(String element){
        int indexOfElem = -1;
        //Vu que indexOf n'a pas l'air d'exister dans cette version je le recrée en quelque sorte
        int i = 0;
        while(indexOfElem == -1 && i < elements.length){
            if(elements[i] == element){
                indexOfElem = i;
            }
            i++;
        }
        return indexOfElem;
    }
    public void spendingElementalSP(CrosscodeCard card, int costSP, int spGain, String elementCard, int elementalCost){
        ///Partie Gain/Coût SP
        LogManager.getLogger(Lea.class.getName()).info("SP AVANT ATTAQUE " + SP_fight + " cout" + costSP);
        SP_fight -= costSP;
        gainSP(spGain);
        LogManager.getLogger(Lea.class.getName()).info("SP APRES ATTAQUE " + SP_fight);
        ///Partie Coût élément
        //Si le coût est trop élevé, on exhauste la carte et on subit des dégâts élémentaires(potentiellement??)
        int indexElem = indexOfElement(elementCard);
        if(indexElem != -1){
            LogManager.getLogger(Lea.class.getName()).info("SPENDING ELEMENTAL" + hasEnoughElem(elementCard, elementalCost));
            if (!hasEnoughElem(elementCard, elementalCost)){
                AbstractPower power;
                switch (elementsStatus[indexElem]) {
                    case "Burn":
                        power = new BurnPower(this, this, elementalCost - elementsValeur[indexElem]); // Crée une instance de BurnPower
                    case "Chill":
                        power = new ChillPower(this, this, elementalCost - elementsValeur[indexElem]); // Crée une instance de ChillPower
                    case "Jolt":
                        power = new JoltPower(this, this, elementalCost - elementsValeur[indexElem]); // Crée une instance de JoltPower
                    case "Mark":
                        power = new MarkPower(this, this, elementalCost - elementsValeur[indexElem]); // Crée une instance de MarkPower
                    default:
                        power = new ProtectionPower(this, 50);
                }
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this,
                    this, power, elementalCost - elementsValeur[indexElem], true, AbstractGameAction.AttackEffect.NONE));

                elementsValeur[indexElem] = 0;
                card.overloadExhaust();
            }else{
                elementsValeur[indexElem] -= elementalCost;
            }
        }


    }

    public int getElementValue(String element){
        return elementsValeur[indexOfElement(element)];
    }
    public void gainElements(Integer[] elementsGain){
        for(int i=0;i<4;i++){
            elementsValeur[i] += elementsGain[i];
        }
    }
    public int getSP_fight(){
        return SP_fight;
    }

    public void gainSP(int SP_gain){
        SP_fight = ((SP_fight + SP_gain) > Max_SP) ? Max_SP : SP_fight + SP_gain;
    }

}
