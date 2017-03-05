import com.intellij.testFramework.ParsingTestCase
import name.kropp.intellij.qml.QmlParserDefinition

class QmlParserTest : ParsingTestCase("parser", "qml", QmlParserDefinition()) {
  fun testExample() = doTest(true)

  override fun getTestDataPath() = "testData"
}