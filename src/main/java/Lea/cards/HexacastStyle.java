package Lea.cards;

import Lea.Abstracts.CrosscodeCard;
import Lea.characters.Lea;
import Lea.patches.AbstractCardEnum;
import Lea.powers.ProtectionPower;
import Lea.powers.powereffect.HexacastStylePower;
import Lea.powers.powereffect.QuadroguardStylePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HexacastStyle extends CrosscodeCard {
    public static final String ID = "leacrosscode:HexacastStyle";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESC = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "img/Lea/cards/Dash.png"; //IMAGE A MODIFIER
    private static final int COST = 1;

    private static final int SP_COST = 0;
    private static final int SP_GAIN = 0;

    private static final int MARK_AMT = 1;
    private static final int UPGRADE_PLUS_MARK = 1;

    public static final Logger logger = LogManager.getLogger(Lea.class.getName());

    public HexacastStyle() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
            AbstractCard.CardType.POWER, AbstractCardEnum.LEA_COBALT,
            AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF, SP_COST, SP_GAIN); //Censé être légendaire on verra si c'est trop fort
        logger.info("HexacastStyle" + DESCRIPTION + "---------------------------------------------------------------------------------------------------------------------------------\n\n");
        magicNumber = baseMagicNumber = MARK_AMT;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new HexacastStylePower(p, magicNumber), magicNumber, true, AbstractGameAction.AttackEffect.SHIELD));
        super.use(p, m);
    }

    @Override
    public AbstractCard makeCopy() {
        logger.info("HexacastStyleCOPY---------------------------------------------------------------------------------------------------------------------------------\n\n");
        return new HexacastStyle();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
        }
    }
}
