package Lea.cards.arts.T1;

import Lea.Abstracts.CrosscodeCard;
import Lea.Abstracts.CrosscodeDamageInfo;
import Lea.Abstracts.HomingTargeting;
import Lea.cards.arts.starter.BulletRain;
import Lea.characters.Lea;
import Lea.enums.TypeDegats;
import Lea.enums.customEnums;
import Lea.patches.AbstractCardEnum;
import Lea.powers.ChillPower;
import Lea.powers.ProtectionPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FrozenStance extends CrosscodeCard {
    public static final String ID = "leacrosscode:FrozenStance";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/Lea/cards/VPRCharge.png";        //IMAGE A CHANGER
    private static final int COST = 1;
    private static final int BLOCK_VALUE = 8;
    private static final int UPGRADE_PLUS_BLOCK = 3;
    private static final int EFFECTS_VALUE = 2;
    private static final int UPGRADE_PLUS_EFFECT = 1;
    private static final int SP_COST = 3;
    private static final int SP_GAIN = 0;

    private static final int COLD_COST = 2;

    private static final TypeDegats TYPE_DEGATS = TypeDegats.COLD;
    public static final Logger logger = LogManager.getLogger(Lea.class.getName());
    public FrozenStance() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
            AbstractCard.CardType.SKILL, AbstractCardEnum.LEA_COBALT,
            customEnums.COMBAT_ART_T1, CardTarget.SELF, SP_COST, SP_GAIN, COLD_COST);
        logger.info("FrozenStance" + DESCRIPTION + "---------------------------------------------------------------------------------------------------------------------------------\n\n");
        this.block=this.baseBlock = BLOCK_VALUE;
        this.magicNumber = this.baseMagicNumber = EFFECTS_VALUE;


        tags.add(customEnums.COLD);
        tags.add(customEnums.SHIELD);
        tags.add(customEnums.COMBAT_ART);
        ElementCard = "Cold";
        tags.add(CardTags.HEALING); //Permet d'empêcher d'obtenir la carte via dead branch
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {


        addToBot(new GainBlockAction(p,
            p, this.block));
        addToBot(new ApplyPowerAction(p,
            p, new ProtectionPower(p, this.magicNumber), this.magicNumber, true, AbstractGameAction.AttackEffect.SHIELD));
        //Iterate through ennemies and apply weak and chill to monsters who intents to attack
        for(AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
            if (!monster.isDeadOrEscaped() && monster.getIntentBaseDmg() >= 0) {
                addToBot(new ApplyPowerAction(monster, p, new WeakPower(monster, this.magicNumber, false), this.magicNumber));
                addToBot(new ApplyPowerAction(monster, p, new ChillPower(monster, p, this.magicNumber), this.magicNumber));
            }
        }

        super.use(p, m);

    }

    @Override
    public AbstractCard makeCopy() {
        logger.info("FrozenStanceCOPY---------------------------------------------------------------------------------------------------------------------------------\n\n");
        return new FrozenStance();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(UPGRADE_PLUS_BLOCK);
            this.upgradeMagicNumber(UPGRADE_PLUS_EFFECT);
        }
    }
}
