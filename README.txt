(EDIT ON MAR 3rd)
My MusicEditorModel is designed in this way:
   Interface: GenericMusicEditorModel
     methods: void edit(Note n, int a, int b, int c)
              void addNote(Note aNewNode)
              void removeNote(Note removed)
              void combineS(List<Note> music);
              void combineC(List<Note> music);
              TreeMap<Integer, List<Note>> getTreeMap()
              String display();
              int getHighestPitch();
              int getLowestPitch();
              int getMaxBeats();
              boolean containsKey(int Beat);
              
           
           
       Class: MusicEditorModel (implements GenericMusicEditorModel)
      fields: TreeMap<Integer, List<Note>> noteMap
              int lowestPitch   0 <= lowestPitch <= 127
              int highestPitch  0 <= highestPitch <= 127
              int maxBeats      0 < maxBeats
     methods: void edit(Note n, int a, int b, int c)
              void addNote(Note aNewNode)
              void removeNote(Note removed)
              void combineS(List<Note> music);
              void combineC(List<Note> music);
              TreeMap<Integer, List<Note>> getTreeMap()
              String display();
              int getHighestPitch();
              int getLowestPitch();
              int getMaxBeats();
              boolean containsKey(int Beat);
              void highestPitch()
              void lowestPitch()
              void maxBeats()
              StringBuilder addSpace(StringBuilder str, int digit)

              
       Class: Note
      fields: private Pitch pitch;
              private int octave;
              private int duration;
              private int startFrom;
              private int instrument;
              private int volume;
     methods: Pitch getPitch()
              int getOctave()
              int getDuration()
              int getStartFrom()
              int getInstrument()
              int getVolume()
              int compareTo(Note n)
              int toInt()
              String toString()
              Note convertToNote(int value)
              void edit(int pitch, int dur, int from)
              boolean equals(Object o)
              int hashCode()
              int pitchInInt()
              
(EDIT ON MAR 24th)
New features in our Model:
	in order not to leak implementation details, we made several changes:
	Interface: GenericMusicEditorModel
	  methods: TreeMap<Integer, List<Note>> getTreeMap() -> List<Note> getAllNotes();
               List<Note> getNotesAtBeat(int beat) (added: since we neglect the importance 
                                                           to get all notes in a single beat last time)
               void setTempo(int tempo) (added)
               int getTempo() (added)
               
        Class: MusicEditorModel
       fields: private int tempo (added: since we need tempo when playing model through midi
                                         we don't know we need tempo last time as well)
	  methods: TreeMap<Integer, List<Note>> getTreeMap() -> List<Note> getAllNotes();
               List<Note> getNotesAtBeat(int beat) (added)
               void setTempo(int tempo) (added)
               int getTempo() (added)
               
  final Class: Builder (implements CompositionBuilder<GenericMusicEditorModel>)
                       (we need builder for us to build a GenericMusicEidtorModel, instead of constructing one manually)
                       (and we put the builder class in MusicEditorModel as required)
               field:  private GenericMusicEditorModel model (Type must be Interface in order not to leak implementation 
                                                              detail)
             methods:  CompositionBuilder<GenericMusicEditorModel> setTempo(int tempo)
                       CompositionBuilder<GenericMusicEditorModel> addNote
                                                                  (int start, int end, int instrument, int pitch, int volume)
                       GenericMusicEditorModel build()

View designed in this way:
    Interface: View
      methods: void display(Model model);

        Class: GuiView (implements View)
       fields: ConcreteGuiViewPanel displayPanel
      methods: void initialize()
               Dimension getPreferredSize()
               void display(Model model)
      
        Class: MidiView (implements View)
       fields: Synthesizer synth;
               Receiver receiver;   
               int currentBeat = 0; // in unit beat
      methods: void playNote(Note note)
               void stopNote(Note note)
               void display(Model model)

        Class: ConsoleView (implements View)
       fields: String console = "";
      methods: String getConsole()
               void display(Model model)

(EDIT ON APR 7th)
;; Model doesn’t change at all
;; add GuiView Interface extends View and make GuiViewImpl Class implements GuiView Interface
;; make Composite Class implements GuiView Interface and takes in both GuiViewImpl and midiViewImpl
;; View changes as required like this:

     Interface: GuiView (extends View)
             ;; GuiView is only for image display or combined image display of Model  

       methods: ConcreteGuiViewPanel getDisplayPanel();
                void repaint();
                void addKeyListener(KeyListener listener);
                void addMouseListener(MouseListener listener);
                void resetFocus();
                void controlPanel(Model model, String s);
                void reset();
                void pause();
                void play(Model model);
                Note getNote(Point p);
 
         Class: GuiViewImpl (implements GuiView)
             ;; GuiViewImpl is only for image display of Model
        fields: ConcreteGuiViewPanel displayPanel;
                JScrollPane scrollPane;
       methods: void initialize()
                Dimension getPreferredSize()
                void display(Model model)
                ConcreteGuiViewPanel getDisplayPanel()
                void repaint()
                void addKeyListener(KeyListener listener)
                void addMouseListener(MouseListener listener)
                void resetFocus()
                void controlPanel(Model model, String s)
                void scrollWindow()
                void reset()
                void pause()
                void play(Model model)
                Note getNote(Point p)

         Class: CompositeViewImpl (implements GuiView)
             ;; this view implementation is for image display combined with midi display of Model
        fields: GuiViewImpl guiView;
                MidiViewImpl midiView;
                Timer timer;
                int count;
       methods: void initialize()
                Dimension getPreferredSize()
                void display(Model model)
                ConcreteGuiViewPanel getDisplayPanel()
                void repaint()
                void addKeyListener(KeyListener listener)
                void addMouseListener(MouseListener listener)
                void resetFocus()
                void controlPanel(Model model, String s)
                void scrollWindow()
                void reset()
                void pause()
                void play(Model model)
                Note getNote(Point p)

How to run terminal: 

locate the java file and the txt file you need to display
input command: java -jar withPartner.jar mystery-1.txt composite (to run the txt file with combined view of guy and midi)
How to use MusicEditor: Press P to play or replay the song
                        Press SPACE to pause and resume the song
                        Mouse click on the panel, Press A will show you new window asking for length, input length, it will add a new Note with inputed length to the required position
                        Mouse click on the panel, Press I will show you new window asking for instruments, input instrument, it will revise the note on the place you click with inputted instrument
                        Mouse click on the panel, Press R will remove the note on the place you clicked 
                        Mouse click on the panel, Press M and don’t released, click mouse and drag it to the place you want, then released, it will move the note to the place you want
                        Mouse click on the panel, Press V will show you new window asking for volume, input volume, it will revise the note on the place you click with inputted volume
                        Mouse click on the Panel, Press D and press mouse not release and drag it to the right side to add more length to it
       
       
       
       
       
       
       
       
       