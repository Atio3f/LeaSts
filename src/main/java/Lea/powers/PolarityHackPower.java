package Lea.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.CorruptionPower;

public class PolarityHackPower extends AbstractPower {
    public static final String POWER_ID = "leacrosscode:PolarityHackPower";
    public static final String NAME = "Polarity Hack";
    public static final String[] DESCRIPTIONS = new String[] {
        "You can't lose more than 2 Jolt per turn."
    };

    private boolean justApplied = false;
    private static final float MAX_LOSE = 2F;

    public PolarityHackPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        updateDescription();
        this.type = PowerType.DEBUFF;
        this.amount = -1;
        this.isTurnBased = false;
        this.priority = 20;
        this.img = new Texture("img/Lea/powers/TargetPower.png");
        if (AbstractDungeon.actionManager.turnHasEnded) {
            this.justApplied = true;
        }
        //On ne peut appliquer l'effet qu'à 1 seul ennemi
        if(!owner.isPlayer){
            for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
                if (!m.isDeadOrEscaped() && m.hasPower("leacrosscode:TargetPower") && m != owner) {
                    m.powers.removeIf(p -> p.ID.equals("leacrosscode:TargetPower"));
                }
            }
        }
    }
    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
