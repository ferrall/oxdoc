package oxdoc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import oxdoc.parser.Parser;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStreamWriter;

@RunWith(JUnit4.class)
public class FileProcessingTest {

  private final static Logger logger = new ConsoleLogger();
  private final static Config config = new Config(logger);
  private final static FileManager fileManager = new FileManager(logger, config);
  private final static TextProcessor textProcessor = new TextProcessor(logger, config);

  @Test
  public void thisAlwaysPasses() throws Exception {
    OxProject project = new OxProject(logger, fileManager, textProcessor);
    File file = new File("example/dist_degen.ox");
    
		ByteArrayOutputStream bufferStream = new ByteArrayOutputStream();
		OutputStreamWriter bufferWriter = new OutputStreamWriter(bufferStream);

		Preprocessor p = new Preprocessor(logger, config, bufferWriter);
		p.processFile(file);

		ByteArrayInputStream bufferIn = new ByteArrayInputStream(bufferStream.toByteArray());

		Parser parser = new Parser(bufferIn, logger, project.addFile(file.getName()), project);
		parser.OxFileDefinition();
  }
}

