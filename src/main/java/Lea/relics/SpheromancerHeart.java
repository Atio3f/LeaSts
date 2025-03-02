package Lea.relics;

import Lea.Abstracts.CrosscodeCharacter;
import Lea.Abstracts.CrosscodeRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SpheromancerHeart extends CrosscodeRelic {
    private static Integer[] elements = new Integer[]{2, 2, 2, 2};
    public static final String ID = "SpheromancerHeart";

    public static final String[] DESCRIPTIONS = new String[] {
        "Give you 3SP at the start of each fight.",
        "+2 of each element."

    };

    public SpheromancerHeart(){
        super(ID, new Texture("img/Lea/relics/SpheromancerHeart.png"),
            RelicTier.STARTER, LandingSound.CLINK, elements);
        updateDescription();

    }


    @Override
    public void atBattleStart() {
        if(AbstractDungeon.player instanceof CrosscodeCharacter){
            ((CrosscodeCharacter)AbstractDungeon.player).gainSP(3);
            super.addElementsValue((CrosscodeCharacter)AbstractDungeon.player);
        }else{
            AbstractDungeon.player.increaseMaxHp(1, true);
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + DESCRIPTIONS[1];
    }
}
