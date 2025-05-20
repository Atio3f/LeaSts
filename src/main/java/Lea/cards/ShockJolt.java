package Lea.cards;

import Lea.Abstracts.CrosscodeCard;
import Lea.Abstracts.CrosscodeCharacter;
import Lea.Abstracts.FrontTargeting;
import Lea.characters.Lea;
import Lea.enums.TypeDegats;
import Lea.enums.customEnums;
import Lea.patches.AbstractCardEnum;
import Lea.powers.JoltPower;
import com.evacipated.cardcrawl.mod.stslib.actions.common.AllEnemyApplyPowerAction;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.ExhaustiveField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
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
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShockJolt extends CrosscodeCard {
    public static final String ID = "leacrosscode:ShockJolt";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/Lea/cards/ShockJolt.png";
    private static final int COST = 1;
    private static final int ATTACK_DMG = 8;
    private static final int UPGRADE_PLUS_DMG = 3;
    private static final int JOLT_AMT = 3;
    private static final int UPGRADE_PLUS_JOLT = 1;
    private static final int SP_COST = 0;
    private static final int SP_GAIN = 0;
    private static final int SHOCK_COST = 1;



    private static final TypeDegats TYPE_DEGATS = TypeDegats.SHOCK;
    public static final Logger logger = LogManager.getLogger(Lea.class.getName());
    public ShockJolt() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
            AbstractCard.CardType.ATTACK, AbstractCardEnum.LEA_COBALT,
            CardRarity.COMMON, CardTarget.ALL_ENEMY, SP_COST, SP_GAIN, SHOCK_COST);
        logger.info("ShockJolt" + DESCRIPTION + "---------------------------------------------------------------------------------------------------------------------------------\n\n");
        this.damage=this.baseDamage = ATTACK_DMG;
        this.magicNumber = this.baseMagicNumber = JOLT_AMT;

        tags.add(customEnums.SHOCK);
        ElementCard = "Shock";

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToTop(new DamageAllEnemiesAction(p,
            this.damage, DamageInfo.DamageType.NORMAL,
            AbstractGameAction.AttackEffect.LIGHTNING));

        //AbstractDungeon.actionManager.addToBottom(new AllEnemyApplyPowerAction(p,this.magicNumber,
        //    (q) -> new JoltPower(q, p, this.magicNumber))); //En gros q ça va contenir chacun des monstres à tour de rôle
        for(AbstractMonster monster : AbstractDungeon.getMonsters().monsters){
            if(!monster.isDeadOrEscaped()){
                addToBot(new ApplyPowerAction(monster, p, new JoltPower(monster, p, this.magicNumber)));
            }
        }
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,
            p, new JoltPower(p, p, this.magicNumber), this.magicNumber, true, AbstractGameAction.AttackEffect.BLUNT_LIGHT));

        System.out.println("TYPE DEGATS " + this.damageTypeForTurn +"" + p.getClass().getSimpleName());
        super.use(p, m);
        /*if(p.getClass().getSuperclass().getSimpleName().equals("CrosscodeCharacter")){
            useCost((CrosscodeCharacter) p);
        }else{
            logger.info("PROB DE CLASSE PERSO------------------------------------------------------" + p.getClass().getSimpleName());
            overloadExhaust();
        }*/
    }

    @Override
    public AbstractCard makeCopy() {
        logger.info("ShockJoltCPY---------------------------------------------------------------------------------------------------------------------------------\n\n");
        return new ShockJolt();
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
