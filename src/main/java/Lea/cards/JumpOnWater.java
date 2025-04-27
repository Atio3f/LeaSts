package Lea.cards;

import Lea.Abstracts.CrosscodeCard;
import Lea.characters.Lea;
import Lea.enums.customEnums;
import Lea.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.colorless.Apparition;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.vfx.combat.GainPowerEffect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//Take 5(2) damage. Gain 1 Intangible. Exhaust
public class JumpOnWater extends CrosscodeCard {
    public static final String ID = "leacrosscode:JumpOnWater";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/Lea/cards/Dash.png"; //IMAGE A CHANGER
    private static final int COST = 1;
    private static final int DAMAGE_TAKEN = 5;
    private static final int UPGRADE_MINUS_DMG = -3;
    private static final int SP_COST = 0;
    private static final int SP_GAIN = 0;


    public static final Logger logger = LogManager.getLogger(Lea.class.getName());
    public JumpOnWater() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
            AbstractCard.CardType.SKILL, CardColor.COLORLESS,
            CardRarity.RARE, AbstractCard.CardTarget.SELF, SP_COST, SP_GAIN);
        logger.info("JumpOnWater" + DESCRIPTION + "---------------------------------------------------------------------------------------------------------------------------------\n\n");
        this.magicNumber = this.baseMagicNumber = DAMAGE_TAKEN;

        tags.add(customEnums.NEUTRAL);
        this.exhaust = true;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new LoseHPAction(p, p, this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, 1), 1));
        super.use(p, m);
    }

    @Override
    public AbstractCard makeCopy() {
        logger.info("JumpOnWaterCOPY---------------------------------------------------------------------------------------------------------------------------------\n\n");
        return new JumpOnWater();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_MINUS_DMG);
        }
    }
}

