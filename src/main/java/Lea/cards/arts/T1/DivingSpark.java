package Lea.cards.arts.T1;

import Lea.Abstracts.CrosscodeCard;
import Lea.Abstracts.CrosscodeDamageInfo;
import Lea.characters.Lea;
import Lea.enums.TypeDegats;
import Lea.enums.customEnums;
import Lea.patches.AbstractCardEnum;
import Lea.powers.JoltPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DivingSpark extends CrosscodeCard {
    public static final String ID = "leacrosscode:DivingSpark";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/Lea/cards/VPRCharge.png";        //IMAGE A CHANGER
    private static final int COST = 2;
    private static final int ATTACK_DMG = 8;
    private static final int UPGRADE_PLUS_DMG = 2;
    private static final int SP_COST = 3;
    private static final int SP_GAIN = 0;

    private static final int JOLT_AMT = 3;
    private static final int UPGRADE_PLUS_JOLT = 1;
    private static final int DMG_PER_JOLT = 3;
    private static final int SHOCK_COST = 1;

    private static final TypeDegats TYPE_DEGATS = TypeDegats.SHOCK;
    public static final Logger logger = LogManager.getLogger(Lea.class.getName());
    public DivingSpark() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
            CardType.ATTACK, AbstractCardEnum.LEA_COBALT,
            customEnums.COMBAT_ART_T1, CardTarget.ENEMY, SP_COST, SP_GAIN, SHOCK_COST);
        logger.info("DivingSpark" + DESCRIPTION + "---------------------------------------------------------------------------------------------------------------------------------\n\n");
        this.damage = this.baseDamage = ATTACK_DMG;
        this.magicNumber = this.baseMagicNumber = JOLT_AMT;


        tags.add(customEnums.SHOCK);
        tags.add(customEnums.DASH);
        tags.add(customEnums.COMBAT_ART);
        ElementCard = "Shock";
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {


        addToTop(new DamageAction(m,
            new CrosscodeDamageInfo(p, this.damage, this.damageTypeForTurn, tags),
            AbstractGameAction.AttackEffect.LIGHTNING));
        addToTop(new ApplyPowerAction(m, p, new JoltPower(m, p, this.magicNumber)));

        if(m.hasPower("leacrosscode:JoltPower")){
            int joltAmt = m.getPower("leacrosscode:JoltPower").amount;
            this.addToBot(new LoseHPAction(m, p, joltAmt * DMG_PER_JOLT));
            addToBot(new RemoveSpecificPowerAction(m, p, "leacrosscode:JoltPower"));
        }

        super.use(p, m);

    }

    @Override
    public AbstractCard makeCopy() {
        logger.info("DivingSparkCOPY---------------------------------------------------------------------------------------------------------------------------------\n\n");
        return new DivingSpark();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DMG);
            this.upgradeMagicNumber(UPGRADE_PLUS_JOLT);
        }
    }
}

