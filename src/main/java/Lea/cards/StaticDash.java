package Lea.cards;

import Lea.Abstracts.CrosscodeCard;
import Lea.characters.Lea;
import Lea.enums.TypeDegats;
import Lea.enums.customEnums;
import Lea.patches.AbstractCardEnum;
import Lea.powers.JoltPower;
import com.evacipated.cardcrawl.mod.stslib.actions.common.AllEnemyApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StaticDash extends CrosscodeCard {
    public static final String ID = "leacrosscode:StaticDash";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/Lea/cards/Dash.png";      //IMAGE A CHANGER
    private static final int COST = 0;
    private static final int CARD_DRAW = 1;
    private static final int UPGRADE_PLUS_CARD = 1;
    private static final int SP_COST = 0;
    private static final int SP_GAIN = 0;

    private static final int BLOCK_VALUE = 2;
    private static final int UPGRADE_BLOCK = 1;

    private static final TypeDegats TYPE_DEGATS = TypeDegats.NEUTRAL;
    public static final Logger logger = LogManager.getLogger(Lea.class.getName());
    public StaticDash() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
            AbstractCard.CardType.SKILL, AbstractCardEnum.LEA_COBALT,
            CardRarity.COMMON, CardTarget.ALL_ENEMY, SP_COST, SP_GAIN);
        logger.info("StaticDash" + DESCRIPTION + "---------------------------------------------------------------------------------------------------------------------------------\n\n");
        this.magicNumber = this.baseMagicNumber = CARD_DRAW;
        this.baseBlock = BLOCK_VALUE;
        tags.add(customEnums.DASH);
        tags.add(customEnums.SHOCK);
        this.exhaust = true;
        this.initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new AllEnemyApplyPowerAction(p,this.magicNumber+1,
            (q) -> new JoltPower(q, p, this.magicNumber+1))); //En gros q ça va contenir chacun des monstres à tour de rôle
        super.use(p, m);
    }

    @Override
    public AbstractCard makeCopy() {
        logger.info("StaticDashCOPY---------------------------------------------------------------------------------------------------------------------------------\n\n");
        return new StaticDash();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(UPGRADE_BLOCK);
            this.upgradeMagicNumber(UPGRADE_PLUS_CARD);
            this.initializeDescription();
        }
    }

    @Override
    public void initializeDescription() {
        this.rawDescription = CardCrawlGame.languagePack.getCardStrings(ID).DESCRIPTION;
        this.rawDescription = this.rawDescription.replace("!J!", Integer.toString(this.magicNumber + 1));
        super.initializeDescription();
    }
}
