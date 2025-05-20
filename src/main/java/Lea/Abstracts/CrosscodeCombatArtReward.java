package Lea.Abstracts;

import basemod.abstracts.CustomReward;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
//A voir si finalement on s'en sert vu que j'ai réussi à faire autrement
public class CrosscodeCombatArtReward extends CustomReward {

    public CrosscodeCombatArtReward(Texture icon, String text, RewardType type) {
        super(icon, text, type);
    }

    public CrosscodeCombatArtReward(TextureRegion icon, String text, RewardType type) {
        super(icon, text, type);
    }

    @Override
    public boolean claimReward() {
        return false;
    }
}
