<#assign site_name_cus = themeDisplay.getThemeSetting('m_sitename')/>
<#assign address = themeDisplay.getThemeSetting('m_address')/>
<#assign phone = themeDisplay.getThemeSetting('m_phone')/>
<#assign email = themeDisplay.getThemeSetting('m_email')/>
<#assign hotline = themeDisplay.getThemeSetting('m_hotline')/>

<#if site_name_cus == ''>
	<#assign site_name_cus = 'Ủy ban nhân dân Tỉnh Phú Thọ'/>
<#else>
	<#assign site_name_cus = themeDisplay.getThemeSetting('m_sitename')/>
</#if>
<#if address == ''>
	<#assign address = 'Đường Trần Phú, phường Tân Dân, thành phố Việt Trì, tỉnh Phú Thọ'/>
<#else>
	<#assign address = themeDisplay.getThemeSetting('m_address')/>
</#if>
<#if phone == ''>
	<#assign phone = '02102.222.555'/>
<#else>
	<#assign phone = themeDisplay.getThemeSetting('m_phone')/>
</#if>
<#if email == ''>
	<#assign email = 'vpub@phutho.gov.vn'/>
<#else>
	<#assign email = themeDisplay.getThemeSetting('m_email')/>
</#if>
<#if hotline == ''>
	<#assign hotline = '02102.222.555'/>
<#else>
	<#assign hotline = themeDisplay.getThemeSetting('m_hotline')/>
</#if>