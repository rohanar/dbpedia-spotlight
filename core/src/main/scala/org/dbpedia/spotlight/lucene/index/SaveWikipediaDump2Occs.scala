/**
 * Copyright 2011 Pablo Mendes, Max Jakob
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.dbpedia.spotlight.lucene.index

import io.Source
import java.io.File
import org.dbpedia.spotlight.io.{AllOccurrenceSource, FileOccurrenceSource, DisambiguationContextSource, WikiOccurrenceSource}
import org.apache.commons.logging.LogFactory
import org.dbpedia.spotlight.string.ContextExtractor
import org.dbpedia.spotlight.util.{IndexingConfiguration, OccurrenceFilter}

/**
 * Saves Occurrences to a TSV file.
 * - Surface forms are taken from anchor texts
 * - Redirects are resolved
 */

object SaveWikipediaDump2Occs {

    private val LOG = LogFactory.getLog(this.getClass)

    def main(args : Array[String]) {
        val indexingConfigFileName = args(0)
        val targetFileName = args(1)

        val config = new IndexingConfiguration(indexingConfigFileName)
        val wikiDumpFileName    = config.get("org.dbpedia.spotlight.data.wikipediaDump")
        val conceptURIsFileName = config.get("org.dbpedia.spotlight.data.conceptURIs")
        val redirectTCFileName  = config.get("org.dbpedia.spotlight.data.redirectsTC")

        LOG.info("Loading concept URIs from "+conceptURIsFileName+"...")
        val conceptURIsSet = Source.fromFile(conceptURIsFileName, "UTF-8").getLines.toSet

        LOG.info("Loading redirects transitive closure from "+redirectTCFileName+"...")
        val redirectsTCMap = Source.fromFile(redirectTCFileName, "UTF-8").getLines.map{ line =>
            val elements = line.split("\t")
            (elements(0), elements(1))
        }.toMap

        val narrowContext = new ContextExtractor(0, 200)

        val filter = new OccurrenceFilter(redirectsTC = redirectsTCMap, conceptURIs = conceptURIsSet, contextExtractor = narrowContext)

        val occs = filter.filter(AllOccurrenceSource.fromXMLDumpFile(new File(wikiDumpFileName)))

        FileOccurrenceSource.writeToFile(occs, new File(targetFileName))

        config.set("org.dbpedia.spotlight.index.occurrences", targetFileName)

    }
}