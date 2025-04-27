package Lea.cards;

import Lea.Abstracts.CrosscodeCard;
import Lea.characters.Lea;
import Lea.enums.TypeDegats;
import Lea.enums.customEnums;
import Lea.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Hackgey extends CrosscodeCard {
    public static final String ID = "leacrosscode:Hackgey";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/Lea/cards/Wait.png";     //IMAGE A CHANGER
    private static final int COST = 1;
    private static final int LOSE_STRENGTH_AMT = 1;
    private static final int UPGRADE_AMT_LOSE = 1;
    private static final int SP_COST = 0;
    private static final int SP_GAIN = 0;



    private static final TypeDegats TYPE_DEGATS = TypeDegats.NEUTRAL;
    public static final Logger logger = LogManager.getLogger(Lea.class.getName());
    public Hackgey() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
            AbstractCard.CardType.SKILL, AbstractCardEnum.LEA_COBALT,
            CardRarity.COMMON, CardTarget.ENEMY, SP_COST, SP_GAIN);
        logger.info("Hackgey" + DESCRIPTION + "---------------------------------------------------------------------------------------------------------------------------------\n\n");
        this.magicNumber = this.baseMagicNumber = LOSE_STRENGTH_AMT;


        this.exhaust = true;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(m, p, new StrengthPower(m, -this.magicNumber), -this.magicNumber));
        this.addToBot(new ApplyPowerAction(m, p, new WeakPower(m, this.magicNumber, false), this.magicNumber));

        super.use(p, m);
    }

    @Override
    public AbstractCard makeCopy() {
        logger.info("HackgeyCOPY---------------------------------------------------------------------------------------------------------------------------------\n\n");
        return new Hackgey();
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_AMT_LOSE);
            this.initializeDescription();
        }
    }
}
