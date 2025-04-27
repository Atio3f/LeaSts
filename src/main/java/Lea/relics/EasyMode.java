package Lea.relics;

import Lea.Abstracts.CrosscodeRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class EasyMode extends CrosscodeRelic {
    private static Integer[] elements = new Integer[]{1, 1, 1, 1};
    public static final String ID = "leacrosscode:EasyMode";
    private static RelicStrings relicStrings = CardCrawlGame.languagePack.getRelicStrings(ID);

    // Get object containing the strings that are displayed in the game.
    public static final String NAME = relicStrings.NAME;
    public static final String DESCRIPTION = relicStrings.DESCRIPTIONS[0];
    public static final String FLAVOR = relicStrings.FLAVOR;

    public EasyMode(){
        super(ID, new Texture("img/Lea/relics/SpheromancerHeart.png" ),//IMAGE A CHANGER
            RelicTier.COMMON, LandingSound.MAGICAL, elements);
        this.description = DESCRIPTION;
        this.flavorText = FLAVOR;
    }
    @Override
    public void atBattleStart() {
        for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
            if (!monster.isDeadOrEscaped() ) {
                this.addToBot(new ApplyPowerAction(monster, AbstractDungeon.player, new StrengthPower(monster, -1), -1));
            }
        }
        super.atBattleStart();
    }

}

