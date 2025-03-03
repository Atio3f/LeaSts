package Lea.relics;

import Lea.Abstracts.CrosscodeRelic;
import com.badlogic.gdx.graphics.Texture;

public class MartialCounterAttack  extends CrosscodeRelic {
    private static Integer[] elements = new Integer[]{0, 1, 0, 0};
    public static final String ID = "leacrosscode:MartialCounterAttack";
    public MartialCounterAttack(){
        super(ID, new Texture("img/Lea/relics/SpheromancerHeart.png" ),//IMAGE A CHANGER
            RelicTier.COMMON, LandingSound.MAGICAL, elements);
    }
}
