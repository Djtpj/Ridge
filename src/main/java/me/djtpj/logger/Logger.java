package me.djtpj.logger;

public class Logger {
    private static Logger instance;

    private Logger() {

    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }

        return instance;
    }

    public static Logger log(String msg, Severity severity) {

        final String formatted = severity.getTAG() + " " + msg;

        if (severity.usesSerr()) System.err.println(formatted);
        else System.out.println(formatted);

        return Logger.instance;
    }
}
