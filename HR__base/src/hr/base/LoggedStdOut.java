package hr.base;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

class LoggedStdOut extends PrintStream {

	PrintStream oldOut;

	PrintStream logPrintStream;

	public LoggedStdOut(String logFilePath) throws FileNotFoundException {
		super(System.out, true);
		logPrintStream = new PrintStream(new File(logFilePath));
		oldOut = System.out;
		System.setOut(this);
	}

	@Override
	public void close() {
		super.close();
		logPrintStream.close();
		System.setOut(oldOut);
	}

	@Override
	public void flush() {
		super.flush();
		logPrintStream.flush();
	}

	@Override
	public void write(byte buf[], int off, int len) {
		try {
			super.write(buf, off, len);
			logPrintStream.write(buf, off, len);
		} catch (Exception e) {
		}
	}
}