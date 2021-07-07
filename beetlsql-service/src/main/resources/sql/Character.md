selectList
====
select * from t_character
where 1 = 1
-- @if(!isBlank(name)){
and name like Concat('%', #{name}, '%')
-- @}
-- @if(!isBlank(description)){
and description like Concat('%', #{description}, '%')
-- @}
-- @if(!isBlank(nationId)){
and nation_id like Concat('%', #{nationId}, '%')
-- @}
-- @if(!isBlank(groupId)){
and group_id like Concat('%', #{groupId}, '%')
-- @}
-- @if(!isBlank(teamId)){
and team_id like Concat('%', #{teamId}, '%')
-- @}
-- @if(!isBlank(appellation)){
and appellation like Concat('%', #{appellation}, '%')
-- @}
-- @if(!isBlank(position)){
and position like Concat('%', #{position}, '%')
-- @}
-- @if(!isBlank(tagList)){
and tag_list like Concat('%', #{tagList}, '%')
-- @}
-- @if(!isBlank(itemObtainApproach)){
and item_obtain_approach like Concat('%', #{itemObtainApproach}, '%')
-- @}
-- @if(!isBlank(profession)){
and profession like Concat('%', #{profession}, '%')
-- @}