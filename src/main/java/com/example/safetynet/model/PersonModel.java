package com.example.safetynet.model;

import com.sun.codemodel.JCodeModel;
import org.jsonschema2pojo.*;
import org.jsonschema2pojo.rules.RuleFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class PersonModel {
public void convertJsonToJavaClass(URL inputJsonUrl, File outputJavaClassDirectory, String model, String Person)
                throws IOException {
                JCodeModel jcodeModel = new JCodeModel();

                GenerationConfig config = new DefaultGenerationConfig() {
                        @Override
                        public boolean isGenerateBuilders() {
                                return true;
                        }

                        @Override
                        public SourceType getSourceType() {
                                return SourceType.JSON;
                        }
                };

                SchemaMapper mapper = new SchemaMapper(new RuleFactory(config, new Jackson2Annotator(config), new SchemaStore()), new SchemaGenerator());
                mapper.generate(jcodeModel, Person, model, "src/main/resources/data.json");

                jcodeModel.build(outputJavaClassDirectory);
        }
}
