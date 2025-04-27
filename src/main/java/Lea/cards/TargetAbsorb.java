package Lea.cards;

import Lea.Abstracts.CrosscodeCard;
import Lea.cards.words.Bye;
import Lea.characters.Lea;
import Lea.enums.TypeDegats;
import Lea.enums.customEnums;
import Lea.patches.AbstractCardEnum;
import Lea.powers.GainSPNextTurnPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//Cout 1(0) Absorb Target from ALL ennemis and gave you 1 Strength per each target Exhaust  Ou p-ê 0 de coût de base et Exhaust retiré quand up #BUFF
public class TargetAbsorb extends CrosscodeCard {
    public static final String ID = "leacrosscode:TargetAbsorb";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/Lea/cards/Wait.png";     //IMAGE A CHANGER
    private static final int COST = 1;
    private static final int UPGRADE_COST = 0;
    private static final int STEAL_VALUE = 1;
    private static final int SP_COST = 0;
    private static final int SP_GAIN = 0;



    private static final TypeDegats TYPE_DEGATS = TypeDegats.NEUTRAL;
    public static final Logger logger = LogManager.getLogger(Lea.class.getName());
    public TargetAbsorb() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
            AbstractCard.CardType.SKILL, AbstractCardEnum.LEA_COBALT,
            CardRarity.COMMON, CardTarget.ALL, SP_COST, SP_GAIN);
        logger.info("TargetAbsorb" + DESCRIPTION + "---------------------------------------------------------------------------------------------------------------------------------\n\n");
        this.magicNumber = this.baseMagicNumber = STEAL_VALUE;


        //tags.add(customEnums.NEUTRAL);
        this.exhaust = true;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int targetAmt = 0;
        for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
            if (!monster.isDeadOrEscaped() && monster.hasPower("leacrosscode:TargetPower")) {
                AbstractPower power = monster.getPower("leacrosscode:TargetPower");
                targetAmt += power.amount;  //In theory, only one ennemi can have Target maybe it will change with a relic or something else?
                monster.powers.removeIf(x -> x.ID.equals("leacrosscode:TargetPower"));
            }
        }
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, targetAmt), targetAmt, true, AbstractGameAction.AttackEffect.NONE));

        super.use(p, m);
    }

    @Override
    public AbstractCard makeCopy() {
        logger.info("TargetAbsorbCOPY---------------------------------------------------------------------------------------------------------------------------------\n\n");
        return new TargetAbsorb();
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(UPGRADE_COST);
            this.initializeDescription();
        }
    }
}
