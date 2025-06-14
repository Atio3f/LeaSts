package Lea.cards;

import Lea.Abstracts.CrosscodeCard;
import Lea.characters.Lea;
import Lea.enums.customEnums;
import Lea.patches.AbstractCardEnum;
import Lea.powers.PreparingAssaultPower;
import Lea.powers.powereffect.LimitlessSpamPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//Carte Preparing assault(Skill) Coût 1 énergie : Next turn, add 3 bullet(+) in your hand
public class PreparingAssault extends CrosscodeCard {
    public static final String ID = "leacrosscode:PreparingAssault";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESC = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "img/Lea/cards/Dash.png"; //IMAGE A MODIFIER
    private static final int COST = 1;

    private static final int BULLET_AMT = 4;   //Génère une BULLET dans la main quand upgrade
    private static final int SP_COST = 0;
    private static final int SP_GAIN = 0;


    public static final Logger logger = LogManager.getLogger(Lea.class.getName());
    public PreparingAssault() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
            CardType.SKILL, AbstractCardEnum.LEA_COBALT,
            CardRarity.COMMON, CardTarget.SELF, SP_COST, SP_GAIN); //Peut être légendaire
        logger.info("PreparingAssault" + DESCRIPTION + "---------------------------------------------------------------------------------------------------------------------------------\n\n");
        this.cardsToPreview = new Bullet();
        magicNumber = baseMagicNumber = BULLET_AMT;
        tags.add(customEnums.NEUTRAL);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new PreparingAssaultPower(p, 3, this.upgraded)));
        super.use(p, m);
    }

    @Override
    public AbstractCard makeCopy() {
        logger.info("PreparingAssaultCOPY---------------------------------------------------------------------------------------------------------------------------------\n\n");
        return new PreparingAssault();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = UPGRADE_DESC;
            Bullet bullet = new Bullet();
            bullet.upgrade();
            this.cardsToPreview = bullet;
            initializeDescription();
        }
    }
}
