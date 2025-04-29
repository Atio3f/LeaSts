package Lea.powers;

import Lea.Abstracts.CrosscodeCharacter;
import Lea.cards.Bullet;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class PreparingAssaultPower extends AbstractPower {
    public static final String POWER_ID = "leacrosscode:PreparingAssaultPower";
    //private static final PowerStrings powerStrings;
    public static final String NAME = "Preparing Assault";
    public static final String[] DESCRIPTIONS = new String[] {
        "Gain ",
        " Bullet next turn."
    };
    public static final String[] UPGRADE_DESCRIPTIONS = new String[] {
        "Gain ",
        " Bullet+ next turn."
    };
    private boolean isUpgraded;
    public PreparingAssaultPower(AbstractCreature owner, int bulletAmount, boolean upgrade) {
        this.name = NAME;
        this.ID = "leacrosscode:PreparingAssaultPower";
        this.owner = owner;
        this.amount = bulletAmount;
        if(isUpgraded){
            this.img = new Texture("img/Lea/powers/ChillPower.png");    //IMAGE A CHANGER FAUT ENCORE UNE AUTRE IMAGE QUAND ELLE EST UP
        }else{
            this.img = new Texture("img/Lea/powers/ChillPower.png");    //IMAGE A CHANGER
        }
        this.updateDescription();
        this.priority = 5;
        isUpgraded = upgrade;
    }

    public void updateDescription() {
        if (this.amount > 1) {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        } else {
            this.description = UPGRADE_DESCRIPTIONS[0] + this.amount + UPGRADE_DESCRIPTIONS[1];
        }

    }

    public void atStartOfTurnPostDraw() {
        this.flash();
        if(owner.isPlayer){
            ((CrosscodeCharacter) owner).gainSP(amount);
        }
        Bullet b = new Bullet();
        if(isUpgraded){
            b.upgrade();
        }
        this.addToBot(new MakeTempCardInHandAction(b, amount));
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "leacrosscode:PreparingAssaultPower"));
    }

    static {
        //powerStrings = CardCrawlGame.languagePack.getPowerStrings("leacrosscode:PreparingAssaultPower");
        //NAME = powerStrings.NAME;
    }
}
