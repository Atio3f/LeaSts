package Lea.patches;

import Lea.interfaces.SPHelper;
import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.CNCardTextColors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import javassist.CtBehavior;

public class SPPatches {

    /*@SpirePatch(

        clz = AbstractPlayer.class,
        method = "preBattlePrep"
    )
    public static class PreBattlePrep {
        public static void Prefix(AbstractPlayer __instance) {
            SPHelper.reset();
        }
    }

    @SpirePatch(
        clz = AbstractDungeon.class,
        method = "render"
    )
    public static class ShowQuests {
        @SpireInsertPatch(
            locator = CNCardTextColors.Locator.class
        )
        public static void Insert(AbstractDungeon __instance, SpriteBatch sb) {
            if (AbstractDungeon.rs == AbstractDungeon.RenderScene.NORMAL) {
                if (CardCrawlGame.isInARun() && AbstractDungeon.currMapNode != null && AbstractDungeon.getCurrRoom() != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
                    {

                    //if (!SPHelper.quests.isEmpty()) {
                    //    SPHelper.render(sb);
                    //}

                        if (AbstractDungeon.isPlayerInDungeon()) {  //Si le joueur est en donjon on affiche les barres élémentaires
                            SPHelper.render(sb);
                        }
                    }
                }
            }
        }

        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractDungeon.class, "getCurrRoom");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }*/
}
