package Lea.cards;

import Lea.Abstracts.CrosscodeCard;
import Lea.characters.Lea;
import Lea.enums.customEnums;
import Lea.patches.AbstractCardEnum;
import Lea.powers.JoltPower;
import Lea.powers.MarkPower;
import Lea.powers.TargetPower;
import com.evacipated.cardcrawl.mod.stslib.actions.common.AllEnemyApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//Cost 0 : Apply 2 Target. Apply 2 Mark to ALL ennemies.
public class Sonar extends CrosscodeCard {
    public static final String ID = "leacrosscode:Sonar";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/Lea/cards/Dash.png"; //IMAGE A CHANGER
    private static final int COST = 0;

    private static final int MAGIC_NUMBER = 2;
    private static final int UPGRADE_PLUS_CARD = 1;

    private static final int SP_COST = 0;
    private static final int SP_GAIN = 0;

    public static final Logger logger = LogManager.getLogger(Lea.class.getName());
    public Sonar() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
            AbstractCard.CardType.SKILL, AbstractCardEnum.LEA_COBALT,
            CardRarity.COMMON, CardTarget.ENEMY, SP_COST, SP_GAIN);
        logger.info("Sonar" + DESCRIPTION + "---------------------------------------------------------------------------------------------------------------------------------\n\n");
        this.magicNumber = this.baseMagicNumber = MAGIC_NUMBER;


        tags.add(customEnums.WAVE);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new AllEnemyApplyPowerAction(p,this.magicNumber,
            (q) -> new MarkPower(q, p, this.magicNumber)));
        addToBot(new ApplyPowerAction(m, p, new TargetPower(m, this.magicNumber)));
        super.use(p, m);
    }

    @Override
    public AbstractCard makeCopy() {
        logger.info("SonarCOPY---------------------------------------------------------------------------------------------------------------------------------\n\n");
        return new Sonar();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_PLUS_CARD);
        }
    }
}
