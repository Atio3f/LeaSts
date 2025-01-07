package Lea.cards;

import Lea.Abstracts.CrosscodeCard;
import Lea.Abstracts.FrontTargeting;
import Lea.characters.Lea;
import Lea.enums.TypeDegats;
import Lea.enums.customEnums;
import Lea.patches.AbstractCardEnum;
import Lea.powers.JoltPower;
import Lea.powers.ProtectionPower;
import basemod.abstracts.CustomCard;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;

public class HitAndBlock extends CrosscodeCard {
    public static final String ID = "leacrosscode:Hit&Block";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/Lea/cards/Hit&Block.png";
    private static final int COST = 1;
    private static final int ATTACK_DMG = 7;
    private static final int UPGRADE_PLUS_DMG = 3;
    private static final int PROT_VALUE = 1;
    private static final int UPGRADE_PLUS_PROT = 1;
    private static final int SP_COST = 0;
    private static final int SP_GAIN = 1;

    private static final TypeDegats TYPE_DEGATS = TypeDegats.NEUTRAL;
    public static final Logger logger = LogManager.getLogger(Lea.class.getName());
    public HitAndBlock() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
            AbstractCard.CardType.ATTACK, AbstractCardEnum.LEA_COBALT,
            AbstractCard.CardRarity.COMMON, AbstractCardEnum.FRONT, SP_COST, SP_GAIN);
        logger.info("HIT&Block" + DESCRIPTION + "---------------------------------------------------------------------------------------------------------------------------------\n\n");

        this.damage=this.baseDamage = ATTACK_DMG;
        this.magicNumber = this.baseMagicNumber = PROT_VALUE;
        tags.add(customEnums.NEUTRAL);
        tags.add(customEnums.MELEE);
        tags.add(customEnums.SHIELD);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCreature target = FrontTargeting.getTarget(this);
        if (target != null) {
            AbstractDungeon.actionManager.addToBottom(new DamageAction(target,
                new DamageInfo(p, this.damage, this.damageTypeForTurn),
                AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
            System.out.println("TYPE DEGATS " + this.damageTypeForTurn);
        }
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,
            p, new ProtectionPower(p, this.magicNumber), this.magicNumber, true, AbstractGameAction.AttackEffect.SHIELD));
        super.use(p, m);
    }

    @Override
    public AbstractCard makeCopy() {
        logger.info("HIT&BLOCKCOPY---------------------------------------------------------------------------------------------------------------------------------\n\n");
        return new HitAndBlock();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DMG);
            this.upgradeMagicNumber(UPGRADE_PLUS_PROT);
        }
    }
}
