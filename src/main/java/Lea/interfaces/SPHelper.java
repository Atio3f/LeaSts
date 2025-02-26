package Lea.interfaces;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.localization.UIStrings;

import java.util.ArrayList;

public class SPHelper {

    public static Integer SPmax;
    public static Integer SPmaxFight;
    public static Integer SPact;
    public static ArrayList<Hitbox> boxes = new ArrayList<>();

    public static final float POSITION_X = 10F * Settings.scale;
    public static final float POSITION_Y = 300F * Settings.scale;

    //public static final Color QUEST_DUPE_BORDER_GLOW_COLOR = new Color(0.2F, 1F, 0.6F, 0.25F);

    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("leacrosscode:QuestLog"); //A CHANGER

    static {
        for (int i = 0; i < 10; i++) {
            boxes.add(new Hitbox(POSITION_X, Settings.HEIGHT - (POSITION_Y + (i * (50F * Settings.scale))), 200F * Settings.scale, 27.5F * Settings.scale));
        }
    }

    public static void render(SpriteBatch sb) {
        sb.setColor(Color.WHITE.cpy());
        FontHelper.renderFontLeftTopAligned(
            sb,
            FontHelper.tipHeaderFont,
            uiStrings.TEXT[0],
            POSITION_X,
            Settings.HEIGHT - POSITION_Y + (50 * Settings.scale),
            Settings.GOLD_COLOR
        );
        int xr = 0;

        //Faut faire pour chaque élément plutôt
        /*
        for (AbstractQuest q : quests) {
            FontHelper.renderFontLeft(
                sb,
                FontHelper.tipHeaderFont,
                q.getName(),
                boxes.get(xr).x,
                boxes.get(xr).y + 12.5F,
                Color.WHITE.cpy()
            );
            for (int i = 0; i < q.goal; i++) {
                ImageHelper.drawTextureScaled(sb, q.progressTex(i), boxes.get(xr).x + (((i * q.getImgSpacing()) + q.textpadding()) * Settings.scale), boxes.get(xr).y - 3);
            }
            xr++;
        }
        */

        for (Hitbox h : boxes) {
            h.render(sb);
        }
    }



    public static void playAcceptQuestSfx() {
        int roll = MathUtils.random(0, 2);
        if (roll == 0) {
            CardCrawlGame.sound.play("BUFF_1");
        } else if (roll == 1) {
            CardCrawlGame.sound.play("BUFF_2");
        } else {
            CardCrawlGame.sound.play("BUFF_3");
        }
    }

    public static void playCompleteQuestSfx() {
        CardCrawlGame.sound.playV("UNLOCK_PING", 0.6F);
    }

    public static void update() {
        for (int i = 0; i < boxes.size(); i++) {
            boxes.get(i).update();
            if (boxes.get(i).hovered) {
                /*
                    if (i < quests.size()) {
                    ImageHelper.tipBoxAtMousePos(quests.get(i).getName(), quests.get(i).getDescription());
                }

                 */
            }
        }
    }

    public static void reset() {
        //quests = new ArrayList<>();
    }


    public static void onSpendingElementalSP(ArrayList<Integer> depenses) {
        //Actualiser avec les nouvelles valeurs les affichages élémentaires et de SP
    }

    public static void onGainingElementalSP(ArrayList<Integer> gains){
        //Actualiser avec les nouvelles valeurs les affichages élémentaires et de SP
    }

}
