package Lea.cards;

import Lea.Abstracts.CrosscodeCard;
import Lea.characters.Lea;
import Lea.enums.TypeDegats;
import Lea.enums.customEnums;
import Lea.patches.AbstractCardEnum;
import Lea.powers.ProtectionPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OverloadCooling extends CrosscodeCard {
    public static final String ID = "leacrosscode:OverloadCooling";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/Lea/cards/Hit&Block.png";    //IMAGE A CHANGER
    private static final int COST = 0;
    private static final int ELEM_VALUE = 2;
    private static final int UPGRADE_PLUS_ELEM = 1;
    private static final int VIGOR_VALUE = 3;
    private static final int SP_COST = 0;
    private static final int SP_GAIN = 0;

    private static final TypeDegats TYPE_DEGATS = TypeDegats.NEUTRAL;
    public static final Logger logger = LogManager.getLogger(Lea.class.getName());
    public OverloadCooling() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
            AbstractCard.CardType.SKILL, AbstractCardEnum.LEA_COBALT,
            CardRarity.BASIC, CardTarget.SELF, SP_COST, SP_GAIN);
        logger.info("OverloadCooling" + DESCRIPTION + "---------------------------------------------------------------------------------------------------------------------------------\n\n");

        this.misc = VIGOR_VALUE;
        this.magicNumber = this.baseMagicNumber = ELEM_VALUE;
        tags.add(customEnums.NEUTRAL);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        /*AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p,
            p, this.block));*/
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,
            p, new VigorPower(p, VIGOR_VALUE), VIGOR_VALUE, true, AbstractGameAction.AttackEffect.NONE));
        super.use(p, m);
    }

    @Override
    public AbstractCard makeCopy() {
        logger.info("OverloadCoolingCOPY---------------------------------------------------------------------------------------------------------------------------------\n\n");
        return new OverloadCooling();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_PLUS_ELEM);
        }
    }
}
