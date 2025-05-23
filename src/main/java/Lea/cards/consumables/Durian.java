package Lea.cards.consumables;

import Lea.Abstracts.CrosscodeCard;
import Lea.characters.Lea;
import Lea.patches.AbstractCardEnum;
import Lea.powers.JoltPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Durian extends CrosscodeCard {
    public static final String ID = "leacrosscode:Durian";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/Lea/cards/Dash.png"; //IMAGE A CHANGER
    private static final int COST = 0;
    private static final int SP_COST = 0;
    private static final int SP_GAIN = 0;

    private static final int UTILISATIONS_MAX = 3; //Affecte le nombre de cartes piochées et les PV Max gagnés
    private static final int UTILISATIONS_UPGRADE = 1; //Affecte le nombre de cartes piochées et les PV Max gagnés après l'amélioration


    public static final Logger logger = LogManager.getLogger(Lea.class.getName());
    public Durian() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
            AbstractCard.CardType.SKILL, AbstractCardEnum.LEA_COBALT,
            CardRarity.RARE, CardTarget.SELF, SP_COST, SP_GAIN);
        logger.info("Durian" + DESCRIPTION + "---------------------------------------------------------------------------------------------------------------------------------\n\n");
        this.misc = this.UTILISATIONS_MAX;
        this.magicNumber = this.baseMagicNumber = this.misc;
        tags.add(CardTags.HEALING); //Permet d'empêcher d'obtenir la carte via dead branch
        this.exhaust = true;

    }

    @Override
    public void applyPowers() {
        //Actualize the
        this.magicNumber = this.baseMagicNumber = this.misc;
        super.applyPowers();
        this.initializeDescription();
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new HealAction(p, p, 8));
        this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, 2), 2));
        this.misc--;
        this.magicNumber = this.baseMagicNumber = this.misc;
        this.initializeDescription();
        if(this.misc==0){
            //SUPPRIMER DU JEU DANS CE CAS JE LAISSE POUR APRES
            for (AbstractCard carteDeck : AbstractDungeon.player.masterDeck.group) {
                if (carteDeck.uuid == this.uuid) {
                    AbstractDungeon.player.masterDeck.removeCard(carteDeck);
                    break;
                }
            }
        }
        initializeDescription();
        super.use(p, m);
    }

    @Override
    public AbstractCard makeCopy() {
        logger.info("DurianCOPY---------------------------------------------------------------------------------------------------------------------------------\n\n");
        return new Durian();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UTILISATIONS_UPGRADE);
            this.misc += 1;
        }
    }
}
//(consommable, Légendaire)  Coût 1 énergie : (!M! Remaining uses) Soigne 8 PV et donne 2 Strength. Exhaust
