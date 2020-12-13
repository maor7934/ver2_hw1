package homework1;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JScrollPane;

/**
 * A JDailog GUI for choosing a GeoSegemnt and adding it to the route shown
 * by RoutDirectionGUI.
 * <p>
 * A figure showing this GUI can be found in homework assignment #1.
 */
public class GeoSegmentsDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	// the RouteDirectionsGUI that this JDialog was opened from
	private RouteFormatterGUI parent;
	
	// a control contained in this 
	private JList<GeoSegment> lstSegments;
	
	/**
	 * Creates a new GeoSegmentsDialog JDialog.
	 * @effects Creates a new GeoSegmentsDialog JDialog with owner-frame
	 * 			owner and parent pnlParent
	 */
	public GeoSegmentsDialog(Frame owner, RouteFormatterGUI pnlParent) {
		// create a modal JDialog with the an owner Frame (a modal window
		// in one that doesn't allow other windows to be active at the
		// same time).
		super(owner, "Please choose a GeoSegment", true);
		
		this.parent = pnlParent;
		
		// TODO Write the body of this method
		//GeoSegment[] legalSegments = pnlParent.getRelevantSegments(ExampleGeoSegments.segments);
		lstSegments = new JList(pnlParent.getRelevantSegments(ExampleGeoSegments.segments));
		JScrollPane scrlSegments = new JScrollPane(lstSegments);
		scrlSegments.setPreferredSize(new Dimension(850, 450));
		JButton btnAddSegment = new JButton("Add GeoSegment");
		btnAddSegment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GeoSegment[] legalSegments = pnlParent.getRelevantSegments(ExampleGeoSegments.segments);
				GeoSegment chosenSegment = lstSegments.getSelectedValue();
				for (int i = 0; i < legalSegments.length ; i++) {
					if (chosenSegment.equals(legalSegments[i])){
						pnlParent.addSegment(chosenSegment);
					}
				}
				//pnlParent.addSegment(chosenSegment);
			}
		});
		
		GeoSegmentsDialog currentDialogPane = this;
		JButton btnCancelChoosing = new JButton("Cancel");
		btnCancelChoosing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentDialogPane.setVisible(false);
			}
		});
		
		
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		this.setLayout(gridbag);
		
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 5;
		c.insets = new Insets(25,25,25,25);
		gridbag.setConstraints(scrlSegments, c);
		this.add(scrlSegments);
		
		
		c.gridx = 0;
		c.gridy = 15;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(25,0,25,25);
		c.anchor = GridBagConstraints.EAST;
		gridbag.setConstraints(btnAddSegment, c);
		this.add(btnAddSegment);

		c.gridx = 0;
		c.gridy = 15;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(25,25,25,0);
		c.anchor = GridBagConstraints.WEST;
		gridbag.setConstraints(btnCancelChoosing, c);
		this.add(btnCancelChoosing);


		
	}
}
