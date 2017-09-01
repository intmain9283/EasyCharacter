package tech.relativelyobjective.easycharacter.races;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.util.LinkedList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import tech.relativelyobjective.easycharacter.characterelements.AbilityModifier;
import tech.relativelyobjective.easycharacter.characterelements.CharacterElementList;
import tech.relativelyobjective.easycharacter.characterelements.Darkvision;
import tech.relativelyobjective.easycharacter.characterelements.Feature;
import tech.relativelyobjective.easycharacter.characterelements.Language;
import tech.relativelyobjective.easycharacter.characterelements.OtherProficiency;
import tech.relativelyobjective.easycharacter.characterelements.SkillProficiency;
import tech.relativelyobjective.easycharacter.characterelements.Spell;
import tech.relativelyobjective.easycharacter.characterelements.WalkSpeed;
import tech.relativelyobjective.easycharacter.utilities.InformationManager;
import tech.relativelyobjective.easycharacter.utilities.Lists;
import tech.relativelyobjective.easycharacter.utilities.WindowManager;

/**
 *
 * @author ReltivlyObjectv
 */
public class Elf {
	public enum ElfSubrace {
		DARK,
		HIGH,
		WOOD
	}
	private static String[] wizardCantrips = {
		"Acid Splash",
		"Chill Touch",
		"Dancing Lights",
		"Fire Bolt",
		"Light",
		"Mage Hand",
		"Mending",
		"Message",
		"Minor Illusion",
		"Poison Spray",
		"Prestidigitation",
		"Ray of Frost",
		"Shocking Grasp",
		"True Strike"
	};
	public static void setup() {
		InformationManager.resetRaceElements();
		InformationManager.addRaceElement(new AbilityModifier(Lists.Ability.DEXTERITY,2));
		InformationManager.addRaceElement(new SkillProficiency(Lists.Skill.PERCEPTION, 1));
		InformationManager.addRaceElement(new Feature(
			"Fey Ancestry",
			"You have advantage on saving throws against being charmed, and "+
			"magic can't put you to sleep."
		));
		InformationManager.addRaceElement(new Feature(
			"Trance",
			"Elves don't need to sleep. Instead, they meditate deeply, remaining "+
			"semiconscious, for 4 hours a day (The Common word for such meditation " +
			"is \"trance\"). While meditating, you can dream after a fashion; "+
			"such dreams are actually mental exercises that have become reflexive "+
			"through years of practice. After resting in this way, you gain the "+
			"same benefit that a human does from 8 hours of sleep."
		));
		InformationManager.addRaceElement(new Language("Common"));
		InformationManager.addRaceElement(new Language("Elvish"));
		WindowManager.getRaceTab().updateRaceElementsList();
		showSubracePrompt();
	}
	private static void showSubracePrompt() {
		JDialog prompt = new JDialog(WindowManager.getMainFrame(), 
			"Elven Subrace", true);
		prompt.setLayout(new BoxLayout(prompt.getContentPane(), BoxLayout.Y_AXIS));
		prompt.setPreferredSize(new Dimension(400,300));
		prompt.setSize(prompt.getPreferredSize());
		//prompt.setMaximumSize(prompt.getPreferredSize());
		prompt.setMinimumSize(prompt.getPreferredSize());
		prompt.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		prompt.add(getSubraceHeaderPanel());
		//prompt.add(getSubraceDetailsPanel());
		prompt.add(getSubraceInputPanel(prompt));
		prompt.setVisible(true);
	}
	private static JPanel getSubraceHeaderPanel() {
		JPanel returnMe = new JPanel();
		returnMe.setLayout(new BorderLayout());
		returnMe.add(new JLabel(
			"<html><center><strong>Select your Elven subrace to gain "+
			"its benefits.</strong></center></html>", JLabel.CENTER), 
			BorderLayout.CENTER);
		return returnMe;
	}
	
	private static JPanel getSubraceInputPanel(JDialog prompt) {
		JPanel returnMe = new JPanel();
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		returnMe.add(new JLabel("Choose Subrace"), constraints);
		JComboBox choice = new JComboBox(Elf.ElfSubrace.values());
		constraints.gridx++;
		returnMe.add(choice, constraints);
		JButton saveButton = new JButton("Save Subrace");
		saveButton.addActionListener((ActionEvent e)->{
			if (choice.getSelectedItem() != null) {
				WindowManager.getRaceTab().updateRaceElementsList();
				prompt.dispose();
				applySubraceEffects((ElfSubrace) choice.getSelectedItem());
			}
		});
		constraints.gridx = 0;
		constraints.gridy++;
		constraints.gridwidth = 2;
		returnMe.add(saveButton, constraints);
		return returnMe;
	}
	private static void applySubraceEffects(ElfSubrace s) {
		switch(s) {
			case DARK:
				InformationManager.addRaceElement(new AbilityModifier(Lists.Ability.CHARISMA,1));
				InformationManager.addRaceElement(new Darkvision(120));
				InformationManager.addRaceElement(new Feature(
					"Sunlight Sensitivity",
					"You have disadvantage on attack rolls and on Wisdom "+
					"(Perception) checks that rely on sight when you, the target "+
					"of your attack, or whatever you are trying to perceive is "+
					"in direct sunlight."
				));
				InformationManager.addRaceElement(new Spell("Dancing Lights",0));
				InformationManager.addRaceElement(new Feature(
					"Drow Magic",
					"When you reach 3rd level, you can cast the faerie fire "+
					"spell once per day. When you reach 5th level, you can also "+
					"cast the darkness spell once per day. Charisma is your "+
					"spellcasting abilily for these spells."
				));
				InformationManager.addRaceElement(new OtherProficiency("Rapier",1));
				InformationManager.addRaceElement(new OtherProficiency("Hand Crossbow",1));
				InformationManager.addRaceElement(new OtherProficiency("Shortsword",1));
				WindowManager.getRaceTab().updateRaceElementsList();
				break;
			case HIGH:
				InformationManager.addRaceElement(new AbilityModifier(Lists.Ability.INTELLIGENCE,1));
				InformationManager.addRaceElement(new Darkvision(60));
				InformationManager.addRaceElement(new OtherProficiency("Longsword",1));
				InformationManager.addRaceElement(new OtherProficiency("Longbow",1));
				InformationManager.addRaceElement(new OtherProficiency("Shortsword",1));
				InformationManager.addRaceElement(new OtherProficiency("Shortbow",1));
				InformationManager.addRaceElement(new WalkSpeed(30));
				WindowManager.getRaceTab().updateRaceElementsList();
				showCantripPrompt();
				break;
			case WOOD:
				InformationManager.addRaceElement(new AbilityModifier(Lists.Ability.WISDOM,1));
				InformationManager.addRaceElement(new Darkvision(60));
				InformationManager.addRaceElement(new OtherProficiency("Longsword",1));
				InformationManager.addRaceElement(new OtherProficiency("Longbow",1));
				InformationManager.addRaceElement(new OtherProficiency("Shortsword",1));
				InformationManager.addRaceElement(new OtherProficiency("Shortbow",1));
				InformationManager.addRaceElement(new WalkSpeed(35));
				InformationManager.addRaceElement(new Feature(
					"Mask of the Wild",
					"You can attempt to hide, even when you are only lightly "+
					"obscured by foliage, heavy rain, falling snow, mist, and "+
					"other natural phenomena."
				));
				WindowManager.getRaceTab().updateRaceElementsList();
				break;
		}
	}
	private static void showCantripPrompt() {
		//Choice to learn a free cantrip
		JDialog prompt = new JDialog(WindowManager.getMainFrame(), "High Elf Cantrip", true);
		prompt.setPreferredSize(new Dimension(500,100));
		prompt.setSize(prompt.getPreferredSize());
		prompt.setMaximumSize(prompt.getPreferredSize());
		prompt.setMinimumSize(prompt.getPreferredSize());
		prompt.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		prompt.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		prompt.add(new JLabel("<html><strong>Select a wizard cantrip to learn</strong><html>"), constraints);
		constraints.gridx = 0;
		constraints.gridy++;
		constraints.gridwidth = 1;
		prompt.add(new JLabel("Cantrip"), constraints);
		JComboBox choice = new JComboBox(wizardCantrips);
		choice.setEditable(true);
		constraints.gridx++;
		prompt.add(choice, constraints);
		JButton saveButton = new JButton("Add Cantrip");
		saveButton.addActionListener((ActionEvent e)->{
			if (choice.getSelectedItem() != null) {
				InformationManager.addRaceElement(
					new Spell((String) choice.getSelectedItem(),0)
				);
				WindowManager.getRaceTab().updateRaceElementsList();
				prompt.dispose();
				showLanguagePrompt();
			}
		});
		constraints.gridx = 0;
		constraints.gridy++;
		constraints.gridwidth = 2;
		prompt.add(saveButton, constraints);
		prompt.setVisible(true);
	
	}
	private static void showLanguagePrompt() {
		//Choice to learn a free cantrip
		JDialog prompt = new JDialog(WindowManager.getMainFrame(), "High Elf Language", true);
		prompt.setPreferredSize(new Dimension(500,100));
		prompt.setSize(prompt.getPreferredSize());
		prompt.setMaximumSize(prompt.getPreferredSize());
		prompt.setMinimumSize(prompt.getPreferredSize());
		prompt.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		prompt.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		prompt.add(new JLabel("<html><strong>Select an additional language to learn</strong><html>"), 
			constraints);
		constraints.gridx = 0;
		constraints.gridy++;
		constraints.gridwidth = 1;
		prompt.add(new JLabel("Language"), constraints);
		LinkedList unknownLanguages = new LinkedList();
		for (String l : Lists.LANGUAGESDND) {
			if (!CharacterElementList.hasLanguage(InformationManager.getRaceElements(), l)) {
				unknownLanguages.add(l);
			}
		}
		JComboBox choice = new JComboBox(unknownLanguages.toArray());
		choice.setEditable(true);
		constraints.gridx++;
		prompt.add(choice, constraints);
		JButton saveButton = new JButton("Add Language");
		saveButton.addActionListener((ActionEvent e)->{
			if (choice.getSelectedItem() != null) {
				InformationManager.addRaceElement(
					new Language((String) choice.getSelectedItem())
				);
				WindowManager.getRaceTab().updateRaceElementsList();
				prompt.dispose();
			}
		});
		constraints.gridx = 0;
		constraints.gridy++;
		constraints.gridwidth = 2;
		prompt.add(saveButton, constraints);
		prompt.setVisible(true);
	}
}
