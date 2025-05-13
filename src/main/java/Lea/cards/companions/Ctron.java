package Lea.cards.companions;

import Lea.Abstracts.CrosscodeCard;
import Lea.cards.Bullet;
import Lea.characters.Lea;
import Lea.enums.TypeDegats;
import Lea.enums.customEnums;
import Lea.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Ctron extends CrosscodeCard {
    public static final String ID = "leacrosscode:Ctron";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESC = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "img/Lea/cards/Wait.png";     //IMAGE A CHANGER
    private static final int COST = 2;
    private static final int UPGRADE_BULLET_AMT = 0;
    private static final int UPGRADE_PLUS_AMT = 1;
    private static final int SP_COST = 0;
    private static final int SP_GAIN = 0;


    private static final TypeDegats TYPE_DEGATS = TypeDegats.NEUTRAL;
    public static final Logger logger = LogManager.getLogger(Lea.class.getName());
    public Ctron() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
            AbstractCard.CardType.SKILL, AbstractCardEnum.LEA_COBALT,
            CardRarity.UNCOMMON, CardTarget.NONE, SP_COST, SP_GAIN);
        logger.info("C'tron" + DESCRIPTION + "---------------------------------------------------------------------------------------------------------------------------------\n\n");
        this.magicNumber = this.baseMagicNumber = UPGRADE_BULLET_AMT;
        tags.add(customEnums.COMPANION);


    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        int handSize = AbstractDungeon.player.hand.size();
        Bullet bullet = new Bullet();
        if (this.upgraded) {

            bullet.upgrade();
            this.addToTop(new MakeTempCardInHandAction(bullet, handSize));
        } else {
            this.addToTop(new MakeTempCardInHandAction(bullet, handSize));
        }

        this.addToTop(new DiscardAction(AbstractDungeon.player, AbstractDungeon.player, handSize, false));
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
            this.upgradeMagicNumber(UPGRADE_PLUS_AMT);
            this.rawDescription = UPGRADE_DESC;
            initializeDescription();
        }
    }
}

