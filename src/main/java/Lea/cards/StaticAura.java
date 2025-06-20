package Lea.cards;

import Lea.Abstracts.CrosscodeCard;
import Lea.characters.Lea;
import Lea.patches.AbstractCardEnum;
import Lea.powers.JoltPower;
import Lea.powers.MarkPower;
import Lea.powers.powereffect.PentafistStylePower;
import Lea.powers.powereffect.StaticAuraPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StaticAura extends CrosscodeCard {
    public static final String ID = "leacrosscode:StaticAura";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESC = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "img/Lea/cards/Dash.png"; //IMAGE A MODIFIER
    private static final int COST = 2;

    private static final int SP_COST = 0;
    private static final int SP_GAIN = 0;

    private static final int JOLT_AMT = 1;
    private static final int UPGRADE_PLUS_JOLT = 1;//pê 2 à voir pour l'équilibrage
    private static final int JOLT_PER_TURN = 1;

    public static final Logger logger = LogManager.getLogger(Lea.class.getName());
    public StaticAura() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
            AbstractCard.CardType.POWER, AbstractCardEnum.LEA_COBALT,
            CardRarity.UNCOMMON, CardTarget.SELF, SP_COST, SP_GAIN); //Censé être légendaire on verra si c'est trop fort
        logger.info("StaticAura" + DESCRIPTION + "---------------------------------------------------------------------------------------------------------------------------------\n\n");
        magicNumber = baseMagicNumber = JOLT_AMT;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //Jolt when used
        for(AbstractMonster monster : AbstractDungeon.getMonsters().monsters){
            if(!monster.isDeadOrEscaped()){
                addToBot(new ApplyPowerAction(monster, p, new JoltPower(monster, p, this.magicNumber)));
            }
        }
        //Power for jolt every turn
        addToBot(new ApplyPowerAction(p, p, new StaticAuraPower(p, JOLT_PER_TURN), JOLT_PER_TURN, true, AbstractGameAction.AttackEffect.NONE));
        super.use(p, m);
    }

    @Override
    public AbstractCard makeCopy() {
        logger.info("StaticAuraeCOPY---------------------------------------------------------------------------------------------------------------------------------\n\n");
        return new StaticAura();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_JOLT);
        }
    }
}
