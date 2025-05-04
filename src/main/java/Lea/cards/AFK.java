package Lea.cards;

import Lea.Abstracts.CrosscodeCard;
import Lea.Abstracts.FrontTargeting;
import Lea.characters.Lea;
import Lea.enums.TypeDegats;
import Lea.enums.customEnums;
import Lea.patches.AbstractCardEnum;
import Lea.powers.BurnPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EquilibriumPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AFK extends CrosscodeCard {
    public static final String ID = "leacrosscode:AFK";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESC = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "img/Lea/cards/Hit.png";
    private static final int COST = 0;
    private static final int SP_COST = 0;
    private static final int SP_GAIN = 1;

    private static final TypeDegats TYPE_DEGATS = TypeDegats.HEAT;
    public static final Logger logger = LogManager.getLogger(Lea.class.getName());
    public AFK() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
            AbstractCard.CardType.SKILL, AbstractCardEnum.LEA_COBALT,
            CardRarity.COMMON, CardTarget.SELF, SP_COST, SP_GAIN);
        logger.info("AFK" + DESCRIPTION + "---------------------------------------------------------------------------------------------------------------------------------\n\n");

        tags.add(customEnums.NEUTRAL);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        addToBot(new ApplyPowerAction(p, p, new EquilibriumPower(p, magicNumber)));
        System.out.println("TYPE DEGATS " + this.damageTypeForTurn);
        super.use(p, m);
    }

    @Override
    public AbstractCard makeCopy() {
        logger.info("AFKCOPY---------------------------------------------------------------------------------------------------------------------------------\n\n");
        return new AFK();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.exhaust = false;
            this.rawDescription = UPGRADE_DESC;
            initializeDescription();
        }
    }

}
