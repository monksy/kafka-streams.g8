package $package$

import java.util.Properties

import org.apache.kafka.streams.scala.Serdes
import org.apache.kafka.streams.{KeyValue, StreamsConfig, Topology, TopologyTestDriver}
import org.scalatest.BeforeAndAfterAll
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import scala.jdk.CollectionConverters._


class WordCountSpec extends AnyWordSpec with Matchers with BeforeAndAfterAll {

  val props = new Properties()
  props.put(StreamsConfig.APPLICATION_ID_CONFIG, StreamSettings.appID)
  props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, StreamSettings.bootstrapServers)

  val topology: Topology = StreamTopology.topology().build()
  val testDriver: TopologyTestDriver = new TopologyTestDriver(topology, props)
  val inputTopic =
    testDriver.createInputTopic(StreamSettings.inputTopic, Serdes.String.serializer(), Serdes.String.serializer())
  val outputTopic =
    testDriver.createOutputTopic(StreamSettings.outputTopic, Serdes.String.deserializer(), Serdes.Long.deserializer())

  "WordCount" should {
    "count words correctly" in {
      inputTopic.pipeInput("", "kafka kafka")
      val expected = List(new KeyValue("kafka", 1L), new KeyValue("kafka", 2L))
      val actual = outputTopic.readKeyValuesToList()
      actual.asScala should equal(expected)
    }
  }

  override def afterAll(): Unit = {
    testDriver.close()
  }

}
