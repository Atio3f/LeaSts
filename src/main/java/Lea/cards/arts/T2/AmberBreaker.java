package Lea.cards.arts.T2;

import Lea.Abstracts.CrosscodeCard;
import Lea.Abstracts.FrontTargeting;
import Lea.characters.Lea;
import Lea.enums.TypeDegats;
import Lea.enums.customEnums;
import Lea.patches.AbstractCardEnum;
import Lea.powers.JoltPower;
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

public class AmberBreaker extends CrosscodeCard {
    public static final String ID = "leacrosscode:AmberBreaker";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/Lea/cards/VPRCharge.png";        //IMAGE A CHANGER
    private static final int COST = 4;
    private static final int ATTACK_DMG = 3;
    private static final int SP_COST = 6;
    private static final int SP_GAIN = 0;
    private static final int NUMBER_HITS = 5;
    private static final int UPGRAGE_PLUS_HITS = 1;
    private static final int JOLT_APPLY = 2;    //Apply 2 Jolt per hit (10/12)

    private static final TypeDegats TYPE_DEGATS = TypeDegats.SHOCK;
    public static final Logger logger = LogManager.getLogger(Lea.class.getName());
    public AmberBreaker() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
            AbstractCard.CardType.ATTACK, AbstractCardEnum.LEA_COBALT,
            customEnums.COMBAT_ART_T2, AbstractCardEnum.FRONT, SP_COST, SP_GAIN);
        logger.info("AmberBreaker" + DESCRIPTION + "---------------------------------------------------------------------------------------------------------------------------------\n\n");
        this.damage=this.baseDamage = ATTACK_DMG;
        this.magicNumber = this.baseMagicNumber = NUMBER_HITS;


        tags.add(customEnums.SHOCK);
        tags.add(customEnums.DASH);
        tags.add(customEnums.COMBAT_ART);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        AbstractCreature cible = FrontTargeting.getTarget(this);
        for(int i=0; i<magicNumber;i++) {

            Lea.logger.info("CIBLE : " + cible);
            if (cible != null) {
                AbstractDungeon.actionManager.addToBottom(new DamageAction(cible,
                    new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.LIGHTNING));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(cible,
                    p, new JoltPower(cible, p, JOLT_APPLY), JOLT_APPLY, true, AbstractGameAction.AttackEffect.BLUNT_LIGHT));

            }
        }


        System.out.println("TYPE DEGATS " + this.damageTypeForTurn);
        super.use(p, m);

    }

    @Override
    public AbstractCard makeCopy() {
        logger.info("AmberBreakerCOPY---------------------------------------------------------------------------------------------------------------------------------\n\n");
        return new AmberBreaker();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            //this.upgradeDamage(UPGRADE_PLUS_DMG);
            this.upgradeMagicNumber(UPGRAGE_PLUS_HITS);
        }
    }
}

