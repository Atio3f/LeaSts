package Lea.Abstracts;

import Lea.characters.Lea;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.Akabeko;
import org.apache.logging.log4j.LogManager;




public class CrosscodeRelic extends CustomRelic {
    Integer[] elementsValeurs = new Integer[]{0, 0, 0, 0}; //Contient les valeurs pour les 4 éléments, ordre : heat, cold, shock, wave
    String[] elementsNom = {"Heat", "Cold", "Shock", "Wave"}; //Contient les noms des éléments pour relier avec la liste elementsValeurs

    public CrosscodeRelic(String id, Texture texture, RelicTier tier, LandingSound sfx, Integer[] elementsValues) {
        super(id, texture, tier, sfx);
        elementsValeurs = elementsValues;
    }

    public CrosscodeRelic(String id, Texture texture, Texture outline, RelicTier tier, LandingSound sfx, Integer[] elementsValues) {
        super(id, texture, outline, tier, sfx);
        elementsValeurs = elementsValues;
    }

    public CrosscodeRelic(String id, String imgName, RelicTier tier, LandingSound sfx, Integer[] elementsValues) {
        super(id, imgName, tier, sfx);
        elementsValeurs = elementsValues;
    }

    //Ajouter les valeurs pour les éléments
    @Override
    public void atBattleStart(){
        LogManager.getLogger(Lea.class.getName()).info("ELEMENTS : " +  elementsValeurs[0] + " " + elementsValeurs[1]+" " + elementsValeurs[2] + " " + elementsValeurs[3]);


        if(AbstractDungeon.player instanceof CrosscodeCharacter){
            LogManager.getLogger(Lea.class.getName()).info("AJOUT ELEMENTS");
            addElementsValue((CrosscodeCharacter)AbstractDungeon.player);
            this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));

        }

    }

    protected void addElementsValue(CrosscodeCharacter p){

         int i = 0;
        for(int value : elementsValeurs){
            p.elementsValeur[i] += value;
            LogManager.getLogger(Lea.class.getName()).info("AJOUT ELEMENTS VALEUR  : "+ p.elementsValeur +"---------------------- ");
            i++;
        }
    }
}
