package Lea.cards;

import Lea.Abstracts.CrosscodeCard;
import Lea.Abstracts.FrontTargeting;
import Lea.characters.Lea;
import Lea.enums.TypeDegats;
import Lea.enums.customEnums;
import Lea.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
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

public class TripleHits extends CrosscodeCard {
    public static final String ID = "leacrosscode:TripleHits";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/Lea/cards/TripleHits.png";
    private static final int COST = 2;
    private static final int ATTACK_DMG = 3;
    private static final int UPGRADE_PLUS_DMG = 2;
    private static final int ALL_DMG = 6;
    private static final int UPGRADE_PLUS_ALL_DMG = 2;
    private static final int SP_COST = 0;
    private static final int SP_GAIN = 1;

    private static final TypeDegats TYPE_DEGATS = TypeDegats.NEUTRAL;
    public static final Logger logger = LogManager.getLogger(Lea.class.getName());
    public TripleHits() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
            AbstractCard.CardType.ATTACK, AbstractCardEnum.LEA_COBALT,
            AbstractCard.CardRarity.COMMON, CardTarget.ALL_ENEMY, SP_COST, SP_GAIN);
        logger.info("TripleHits" + DESCRIPTION + "---------------------------------------------------------------------------------------------------------------------------------\n\n");
        this.damage=this.baseDamage = ATTACK_DMG;
        magicNumber = baseMagicNumber = ALL_DMG;
        tags.add(customEnums.NEUTRAL);
        tags.add(customEnums.MELEE);


    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //On tape 2 fois l'ennemi de devant avec peu de dégâts(damage) puis la grosse attaque sur tous les ennemis(magic number)
        for(int i=0;i<2;i++) {
            AbstractCreature target = FrontTargeting.getTarget(this);
            if (target != null) {
                AbstractDungeon.actionManager.addToBottom(new DamageAction(target,
                    new DamageInfo(p, this.damage, this.damageTypeForTurn),
                    AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
            }
        }
        this.isMultiDamage = true;
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.magicNumber, this.damageTypeForTurn,
            AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        this.isMultiDamage = false;
        System.out.println("TYPE DEGATS " + this.damageTypeForTurn);

        super.use(p, m);
    }

    @Override
    public AbstractCard makeCopy() {
        logger.info("HITCOPY---------------------------------------------------------------------------------------------------------------------------------\n\n");
        return new TripleHits();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DMG);
            this.upgradeMagicNumber(UPGRADE_PLUS_ALL_DMG);
        }
    }
}
