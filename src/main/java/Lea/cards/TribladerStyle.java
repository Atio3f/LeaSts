package Lea.cards;

import Lea.Abstracts.CrosscodeCard;
import Lea.characters.Lea;
import Lea.patches.AbstractCardEnum;
import Lea.powers.ProtectionPower;
import Lea.powers.powereffect.QuadroguardStylePower;
import Lea.powers.powereffect.TribladerStylePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TribladerStyle extends CrosscodeCard {
    public static final String ID = "leacrosscode:TribladerStyle";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "img/Lea/cards/Dash.png"; //IMAGE A MODIFIER
    private static final int COST = 2;

    private static final int SP_COST = 0;
    private static final int SP_GAIN = 0;

    private static final int VIGOR_AMT = 3;
    private static final int UPGRADE_PLUS_DEX = 1;
    private static final int VIGOR_PROC = 1;

    public static final Logger logger = LogManager.getLogger(Lea.class.getName());
    public TribladerStyle() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
            AbstractCard.CardType.POWER, AbstractCardEnum.LEA_COBALT,
            CardRarity.UNCOMMON, CardTarget.SELF, SP_COST, SP_GAIN); //Censé être légendaire on verra si c'est trop fort
        logger.info("TribladerStyle" + DESCRIPTION + "---------------------------------------------------------------------------------------------------------------------------------\n\n");
        magicNumber = baseMagicNumber = VIGOR_AMT;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new VigorPower(p, this.magicNumber), this.magicNumber));
        addToBot(new ApplyPowerAction(p, p, new TribladerStylePower(p, VIGOR_PROC), VIGOR_PROC, true, AbstractGameAction.AttackEffect.NONE));
        super.use(p, m);
    }

    @Override
    public AbstractCard makeCopy() {
        logger.info("TribladerStyleCOPY---------------------------------------------------------------------------------------------------------------------------------\n\n");
        return new TribladerStyle();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.isInnate = true;
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}


