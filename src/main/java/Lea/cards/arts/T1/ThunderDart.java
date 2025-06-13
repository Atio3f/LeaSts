package Lea.cards.arts.T1;

import Lea.Abstracts.CrosscodeCard;
import Lea.characters.Lea;
import Lea.enums.TypeDegats;
import Lea.enums.customEnums;
import Lea.patches.AbstractCardEnum;
import Lea.powers.ChillPower;
import Lea.powers.JoltPower;
import Lea.powers.ProtectionPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ThunderDart extends CrosscodeCard {
    public static final String ID = "leacrosscode:ThunderDart";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/Lea/cards/VPRCharge.png";        //IMAGE A CHANGER
    private static final int COST = 1;
    private static final int ATTACK_DMG = 5;
    private static final int UPGRADE_PLUS_DMG = 2;
    private static final int BLOCK_VALUE = 5;
    private static final int UPGRADE_PLUS_BLOCK = 2;
    private static final int SP_COST = 3;
    private static final int SP_GAIN = 0;

    private static final int JOLT_AMT = 2;
    private static final int UPGRADE_PLUS_JOLT = 1;
    private static final int SHOCK_COST = 2;

    private static final TypeDegats TYPE_DEGATS = TypeDegats.SHOCK;
    public static final Logger logger = LogManager.getLogger(Lea.class.getName());
    public ThunderDart() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
            AbstractCard.CardType.SKILL, AbstractCardEnum.LEA_COBALT,
            customEnums.COMBAT_ART_T1, CardTarget.ALL_ENEMY, SP_COST, SP_GAIN, SHOCK_COST);
        logger.info("ThunderDart" + DESCRIPTION + "---------------------------------------------------------------------------------------------------------------------------------\n\n");
        this.damage = this.baseDamage = ATTACK_DMG;
        this.block=this.baseBlock = BLOCK_VALUE;
        this.magicNumber = this.baseMagicNumber = JOLT_AMT;


        tags.add(customEnums.SHOCK);
        tags.add(customEnums.DASH);
        tags.add(customEnums.COMBAT_ART);
        ElementCard = "Shock";
        tags.add(CardTags.HEALING); //Permet d'empêcher d'obtenir la carte via dead branch
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {


        addToTop(new GainBlockAction(p, p, this.block));
        addToTop(new DamageAllEnemiesAction(p,
            this.damage, DamageInfo.DamageType.NORMAL,
            AbstractGameAction.AttackEffect.LIGHTNING));
        for(AbstractMonster monster : AbstractDungeon.getMonsters().monsters){
            if(!monster.isDeadOrEscaped()){
                addToBot(new ApplyPowerAction(monster, p, new JoltPower(monster, p, this.magicNumber), this.magicNumber, true));
            }
        }

        super.use(p, m);

    }

    @Override
    public AbstractCard makeCopy() {
        logger.info("ThunderDartCOPY---------------------------------------------------------------------------------------------------------------------------------\n\n");
        return new ThunderDart();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DMG);
            this.upgradeBlock(UPGRADE_PLUS_BLOCK);
            this.upgradeMagicNumber(UPGRADE_PLUS_JOLT);
        }
    }
}
