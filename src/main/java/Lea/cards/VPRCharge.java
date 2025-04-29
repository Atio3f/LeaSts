package Lea.cards;

import Lea.Abstracts.CrosscodeCard;
import Lea.Abstracts.CrosscodeDamageInfo;
import Lea.Abstracts.FrontTargeting;
import Lea.characters.Lea;
import Lea.enums.TypeDegats;
import Lea.enums.customEnums;
import Lea.patches.AbstractCardEnum;
import Lea.powers.TargetPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class VPRCharge extends CrosscodeCard {
    public static final String ID = "leacrosscode:VPRCharge";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/Lea/cards/VPRCharge.png";
    private static final int COST = 1;
    private static final int ATTACK_DMG = 8;
    private static final int UPGRADE_PLUS_DMG = 3;
    private static final int SP_COST = 0;
    private static final int SP_GAIN = 1;
    private static final int UPGRADE_PLUS_SP_GAIN = 1;
    private static final int TARGET_AMT = 2;
    private static final int UPGRAGE_PLUS_TARGET = 1;

    private static final TypeDegats TYPE_DEGATS = TypeDegats.NEUTRAL;
    public static final Logger logger = LogManager.getLogger(Lea.class.getName());
    public VPRCharge() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
            AbstractCard.CardType.ATTACK, AbstractCardEnum.LEA_COBALT,
            CardRarity.COMMON, CardTarget.ENEMY, SP_COST, SP_GAIN);
        logger.info("VPRCharge" + DESCRIPTION + "---------------------------------------------------------------------------------------------------------------------------------\n\n");
        this.damage=this.baseDamage = ATTACK_DMG;
        this.magicNumber = this.baseMagicNumber = TARGET_AMT;


        tags.add(customEnums.NEUTRAL);
        tags.add(customEnums.BULLET);


    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        AbstractDungeon.actionManager.addToBottom(new DamageAction(m,
                new CrosscodeDamageInfo(p, this.damage, this.damageTypeForTurn, tags),
                AbstractGameAction.AttackEffect.SMASH));
        //A changer pour l'effet de Target quand il sera implémenté
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new TargetPower(m, this.magicNumber), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));

        System.out.println("TYPE DEGATS " + this.damageTypeForTurn);
        super.use(p, m);

    }

    @Override
    public AbstractCard makeCopy() {
        logger.info("VPRChargeCOPY---------------------------------------------------------------------------------------------------------------------------------\n\n");
        return new VPRCharge();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DMG);
            this.upgradeMagicNumber(UPGRAGE_PLUS_TARGET);
            this.SP_Gain += UPGRADE_PLUS_SP_GAIN;
        }
    }
}
