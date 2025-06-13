package Lea.cards;

import Lea.Abstracts.CrosscodeCard;
import Lea.characters.Lea;
import Lea.enums.TypeDegats;
import Lea.enums.customEnums;
import Lea.patches.AbstractCardEnum;
import Lea.powers.ProtectionPower;
import Lea.powers.TargetPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShieldAndFocus extends CrosscodeCard {
    public static final String ID = "leacrosscode:ShieldAndFocus";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/Lea/cards/Hit&Block.png";    //IMAGE A CHANGER
    private static final int COST = 1;
    private static final int PROT_VALUE = 5;
    private static final int TARGET_AMT = 2;
    private static final int UPGRADE_PLUS_TARGET = 2;
    private static final int SP_COST = 0;
    private static final int SP_GAIN = 0;

    private static final TypeDegats TYPE_DEGATS = TypeDegats.NEUTRAL;
    public static final Logger logger = LogManager.getLogger(Lea.class.getName());
    public ShieldAndFocus() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
            AbstractCard.CardType.SKILL, AbstractCardEnum.LEA_COBALT,
            CardRarity.COMMON, CardTarget.ENEMY, SP_COST, SP_GAIN);
        logger.info("Shield&Focus" + DESCRIPTION + "---------------------------------------------------------------------------------------------------------------------------------\n\n");


        this.magicNumber = this.baseMagicNumber = TARGET_AMT;
        tags.add(customEnums.NEUTRAL);
        tags.add(customEnums.SHIELD);
        tags.add(CardTags.STARTER_DEFEND);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p,
            p, new ProtectionPower(p, PROT_VALUE), PROT_VALUE, true, AbstractGameAction.AttackEffect.SHIELD));
        addToBot(new ApplyPowerAction(m, p, new TargetPower(m, this.magicNumber), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));

        super.use(p, m);
    }

    @Override
    public AbstractCard makeCopy() {
        logger.info("Shield&FocusCOPY---------------------------------------------------------------------------------------------------------------------------------\n\n");
        return new ShieldAndFocus();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_PLUS_TARGET);
        }
    }
}
