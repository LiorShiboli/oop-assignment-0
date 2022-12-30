package observer;

public class ConcreteMember implements Member {
    private UndoableStringBuilder usb;
    private String name;

    private int minLength;
    private int maxlength;
    private String addToEnd;

    public ConcreteMember(String name) {
        this.name = name;
        this.minLength = 0;
        this.maxlength = Integer.MAX_VALUE;
        this.addToEnd = null;
    }

    public ConcreteMember(String name, int minLength,int maxlength, String addToEnd) {
        this.name = name;
        this.minLength = Math.max(minLength, 0);
        this.maxlength = Math.max(maxlength,0);
        if (this.minLength == 0) {
            this.addToEnd = null;
        } else {
            this.addToEnd = (addToEnd == null || addToEnd.length() == 0) ? "null" : addToEnd;
        }
    }

    public UndoableStringBuilder getUndoableStringBuilder() {
        return usb;
    }

    @Override
    public void update(UndoableStringBuilder usb) {
        this.usb = usb; // and this is stupid

        System.out.println("INFO: Member " + name + " updated.");
        if(usb.toString().length()>maxlength){
            usb.delete(maxlength,usb.toString().length());
        }
        int needed = minLength - usb.toString().length();
        if (needed > 0) {
            // create the str that append to the usb
            StringBuilder neededBuilder = new StringBuilder();
            while (needed > 0) {
                neededBuilder.append(this.addToEnd);
                needed -= this.addToEnd.length();
            }

            // update the usb
            usb.append(neededBuilder.toString());
        }
    }
}
