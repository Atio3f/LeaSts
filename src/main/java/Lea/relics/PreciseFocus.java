package Lea.relics;

import Lea.Abstracts.CrosscodeRelic;
import com.badlogic.gdx.graphics.Texture;

//Target increases the damage a lot more
public class PreciseFocus extends CrosscodeRelic {
    private static Integer[] elements = new Integer[]{0, 0, 0, 1};
    public static final String ID = "leacrosscode:PreciseFocus";
    public PreciseFocus(){
        super(ID, new Texture("img/Lea/relics/SpheromancerHeart.png" ),//IMAGE A CHANGER Changer pour FocusSenses plus tard
            RelicTier.COMMON, LandingSound.MAGICAL, elements);
    }
}
