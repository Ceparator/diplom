/*
 * Copyright 2004-2014 ICEsoft Technologies Canada Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS
 * IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */

package org.icefaces.samples.showcase.example.core.navigationNotifier;

import org.icefaces.samples.showcase.metadata.annotation.ComponentExample;
import org.icefaces.samples.showcase.metadata.annotation.ExampleResource;
import org.icefaces.samples.showcase.metadata.annotation.ExampleResources;
import org.icefaces.samples.showcase.metadata.annotation.ResourceType;
import org.icefaces.samples.showcase.metadata.annotation.Menu;
import org.icefaces.samples.showcase.metadata.annotation.MenuLink;
import org.icefaces.samples.showcase.metadata.context.ComponentExampleImpl;

import javax.annotation.PostConstruct;
import javax.faces.bean.CustomScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;

@ComponentExample(
        title = "Navigation Notifier Component",
        description = "The <b>icecore:navigationNotifier</b> component will notify the application when the page shown in the browser was rendered as a result of a browser navigation by using 'back' or 'forward' browser buttons.",
        example = "/resources/examples/core/navigation-notifier.xhtml"
)

@ExampleResources(
        resources = {
                @ExampleResource(type = ResourceType.xhtml,
                        title="navigation-notifier.xhtml",
                        resource = "/resources/examples/core/navigation-notifier.xhtml"),
                @ExampleResource(type = ResourceType.xhtml,
                        title="navigation-notifier-next.xhtml",
                        resource = "/resources/examples/core/navigation-notifier-next.xhtml"),
                @ExampleResource(type = ResourceType.java,
                        title="NavigationNotifierBean.java",
                        resource = "/WEB-INF/classes/org/icefaces/samples/showcase/example/core/navigationNotifier/NavigationNotifierBean.java"),
                @ExampleResource(type = ResourceType.java,
                        title="NavigationNotifierViewScopeBean.java",
                        resource = "/WEB-INF/classes/org/icefaces/samples/showcase/example/core/navigationNotifier/NavigationNotifierViewScopeBean.java")
        }
)
@Menu(
    title = "menu.core.navigationNotifierBean.subMenu.title", 
    menuLinks = {
        @MenuLink(title = "menu.core.navigationNotifierBean.subMenu.main", isDefault = true, exampleBeanName = NavigationNotifierBean.BEAN_NAME)
    }
)
@ManagedBean(name = NavigationNotifierBean.BEAN_NAME)
@CustomScoped(value = "#{window}")
public class NavigationNotifierBean extends ComponentExampleImpl<NavigationNotifierBean> implements Serializable {
    public static final String BEAN_NAME = "navigationNotifierBean";
	public String getBeanName() { return BEAN_NAME; }

    public NavigationNotifierBean() {
        super(NavigationNotifierBean.class);
    }

    @PostConstruct
    public void initMetaData() {
        super.initMetaData();
    }
}
