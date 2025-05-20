package Lea.actions;

import Lea.Abstracts.CrosscodeCharacter;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

public class RegainElementalAction extends AbstractGameAction {


    public RegainElementalAction(AbstractPlayer player, Integer[] elementsGains) {

        ((CrosscodeCharacter) player).gainElements(elementsGains);

    }

    @Override
    public void update() {
        isDone = true;
    }
}
