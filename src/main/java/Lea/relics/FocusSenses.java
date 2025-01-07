package Lea.relics;

import Lea.Abstracts.CrosscodeRelic;
import com.badlogic.gdx.graphics.Texture;

//Target increases the damage a lot more
public class FocusSenses extends CrosscodeRelic {
    private static Integer[] elements = new Integer[]{0, 0, 0, 1};
    public static final String ID = "leacrosscode:FocusSenses";
    public FocusSenses(){
        super(ID, new Texture("img/Lea/relics/SpheromancerHeart.png" ),//Changer pour FocusSenses plus tard
            RelicTier.COMMON, LandingSound.MAGICAL, elements);
    }
}
