package name.kropp.intellij.qml

import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.ide.util.treeView.smartTree.TreeElement
import com.intellij.navigation.NavigationItem
import name.kropp.intellij.qml.psi.QmlAttributeAssignment
import name.kropp.intellij.qml.psi.QmlObject

class QmlStructureViewElement(private val element: QmlObject) : StructureViewTreeElement {
  override fun getPresentation() = QmlObjectStructureViewPresentation(element)

  override fun getChildren(): Array<TreeElement> {
    return element.body.children.filterIsInstance<QmlAttributeAssignment>().map {
      it.`object`?.let { QmlStructureViewElement(it) } ?: QmlStructureViewPropertyElement(it)
    }.toTypedArray()
  }

  override fun getValue() = element

  override fun canNavigate() = (element as? NavigationItem)?.canNavigate() == true
  override fun canNavigateToSource() = (element as? NavigationItem)?.canNavigateToSource() == true

  override fun navigate(requestFocus: Boolean) {
    (element as? NavigationItem)?.navigate(requestFocus)
  }
}