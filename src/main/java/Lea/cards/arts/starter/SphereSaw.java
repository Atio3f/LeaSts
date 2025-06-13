package Lea.cards.arts.starter;

import Lea.Abstracts.CrosscodeCard;
import Lea.Abstracts.FrontTargeting;
import Lea.characters.Lea;
import Lea.enums.TypeDegats;
import Lea.enums.customEnums;
import Lea.patches.AbstractCardEnum;
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

public class SphereSaw extends CrosscodeCard {
    public static final String ID = "leacrosscode:SphereSaw";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/Lea/cards/SphereSaw.png";
    private static final int COST = 1;
    private static final int ATTACK_DMG = 12;
    private static final int UPGRADE_PLUS_DMG = 4;
    private static final int VULNERABLE_AMT = 1;
    private static final int UPGRADE_PLUS_VULNERABLE = 1;
    private static final int SP_COST = 2;
    private static final int SP_GAIN = 0;

    private static final TypeDegats TYPE_DEGATS = TypeDegats.NEUTRAL;
    public static final Logger logger = LogManager.getLogger(Lea.class.getName());
    public SphereSaw() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        AbstractCard.CardType.ATTACK, AbstractCardEnum.LEA_COBALT,
        customEnums.COMBAT_ART_STARTER, AbstractCardEnum.FRONT, SP_COST, SP_GAIN);
        logger.info("SPHERESAW" + DESCRIPTION + "---------------------------------------------------------------------------------------------------------------------------------\n\n");
        this.damage=this.baseDamage = ATTACK_DMG;
        this.magicNumber = this.baseMagicNumber = VULNERABLE_AMT;

        tags.add(CardTags.STRIKE); //This tag marks it as a Strike card for the purposes of Perfected Strike and any similar modded effects
        tags.add(customEnums.NEUTRAL);
        tags.add(customEnums.MELEE);
        tags.add(customEnums.COMBAT_ART);
        tags.add(CardTags.HEALING); //Permet d'empêcher d'obtenir la carte via dead branch

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCreature target = FrontTargeting.getTarget(this);
        if (target != null) {
            AbstractDungeon.actionManager.addToBottom(new DamageAction(target,
                new DamageInfo(p, this.damage, this.damageTypeForTurn),
                AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, p, new VulnerablePower(target, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));

        }
        super.use(p, m);
        System.out.println("TYPE DEGATS " + this.damageTypeForTurn);
    }

    @Override
    public AbstractCard makeCopy() {
        logger.info("SPHERESAW---------------------------------------------------------------------------------------------------------------------------------\n\n");
        return new SphereSaw();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DMG);
            this.upgradeMagicNumber(UPGRADE_PLUS_VULNERABLE);
        }
    }
}
